package me.seproject.mavenproject1.controller;

import me.seproject.mavenproject1.db.DBConnection;
import me.seproject.mavenproject1.entity.Payment;
import me.seproject.mavenproject1.entity.PaymentGateway;
import me.seproject.mavenproject1.entity.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PaymentController {

    private String paymentMethod;
    private String transactionStatus;
    private int queueSize;
    private PaymentGateway gateway;

    public PaymentController() {
        this.gateway = new PaymentGateway();
    }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }
    public int getQueueSize() { return queueSize; }
    public void setQueueSize(int queueSize) { this.queueSize = queueSize; }

    public boolean validateCredentials(String studentID) {
        return studentID != null && !studentID.isEmpty();
    }

    public boolean computeFees(String studentID) {
        return true;
    }

    public double getFeeAmount(String studentID) {
        return 5250.00;
    }

    public void choosePaymentMethod(String method) {
        this.paymentMethod = method;
        this.transactionStatus = "METHOD_SELECTED";
    }

    public void processQueue() {
        queueSize = Math.max(0, queueSize - 1);
    }

    public boolean authorizeTransaction(double amount, String method) {
        try {
            Map<String, Object> details = new HashMap<>();
            details.put("amount", amount);
            details.put("method", method);
            details.put("timestamp", System.currentTimeMillis());
            boolean authorized = gateway.makePayment((float) amount, details);
            if (authorized) {
                this.transactionStatus = "AUTHORIZED";
            }
            return authorized;
        } catch (Exception e) {
            this.transactionStatus = "AUTHORIZATION_FAILED";
            return false;
        }
    }

    public boolean confirmPayment(Payment payment) {
        try {
            boolean confirmed = gateway.confirmPayment(payment.getTransactionID());
            if (confirmed) {
                payment.setStatus("COMPLETED");
                this.transactionStatus = "COMPLETED";
            }
            return confirmed;
        } catch (Exception e) {
            this.transactionStatus = "CONFIRMATION_FAILED";
            return false;
        }
    }

    public void updateFinancialRecord() {
        this.transactionStatus = "RECORD_UPDATED";
    }

    public Receipt generateReceipt(Payment payment) {
        Receipt receipt = new Receipt();
        receipt.setReceiptID("RCP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        receipt.setPaymentID(payment.getPaymentID());
        receipt.setStudentID(payment.getStudentID());
        receipt.setAmount((float) payment.getAmount());
        receipt.setPaymentMethod(payment.getPaymentMethod());
        receipt.setTransactionID(payment.getTransactionID());
        if (payment.getPaymentDate() != null) {
            receipt.setPaymentDate(java.util.Date.from(
                payment.getPaymentDate().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        }
        savePayment(payment);
        saveReceipt(receipt);
        return receipt;
    }

    public void rollback() {
        this.paymentMethod = null;
        this.transactionStatus = "ROLLED_BACK";
    }

    // --- DB: Payments ---

    public void savePayment(Payment payment) {
        String sql = "INSERT INTO BUE.PAYMENTS (paymentID, studentID, amount, paymentMethod, paymentDate, status, transactionID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String pid = payment.getPaymentID() != null ? payment.getPaymentID()
                         : "PAY" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            payment.setPaymentID(pid);
            ps.setString(1, pid);
            ps.setString(2, payment.getStudentID());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getPaymentMethod());
            ps.setTimestamp(5, payment.getPaymentDate() != null
                ? Timestamp.valueOf(payment.getPaymentDate())
                : Timestamp.valueOf(LocalDateTime.now()));
            ps.setString(6, payment.getStatus());
            ps.setString(7, payment.getTransactionID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving payment: " + e.getMessage());
        }
    }

    public List<Payment> getPaymentsByStudent(String studentID) {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM BUE.PAYMENTS WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Payment p = new Payment();
                p.setPaymentID(rs.getString("paymentID"));
                p.setStudentID(rs.getString("studentID"));
                try { p.setAmount(rs.getDouble("amount")); } catch (Exception ignored) {}
                p.setPaymentMethod(rs.getString("paymentMethod"));
                p.setStatus(rs.getString("status"));
                p.setTransactionID(rs.getString("transactionID"));
                Timestamp ts = rs.getTimestamp("paymentDate");
                if (ts != null) p.setPaymentDate(ts.toLocalDateTime());
                payments.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching payments: " + e.getMessage());
        }
        return payments;
    }

    // --- DB: Receipts ---

    public void saveReceipt(Receipt receipt) {
        String sql = "INSERT INTO BUE.RECEIPTS (receiptID, paymentID, studentID, amount, paymentDate, paymentMethod, transactionID) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, receipt.getReceiptID());
            ps.setString(2, receipt.getPaymentID());
            ps.setString(3, receipt.getStudentID());
            ps.setFloat(4, receipt.getAmount());
            ps.setTimestamp(5, receipt.getPaymentDate() != null
                ? new Timestamp(receipt.getPaymentDate().getTime())
                : new Timestamp(System.currentTimeMillis()));
            ps.setString(6, receipt.getPaymentMethod());
            ps.setString(7, receipt.getTransactionID());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving receipt: " + e.getMessage());
        }
    }

    // --- DB: Saved Payment Methods ---

    public void savePaymentMethod(String studentID, String methodType, String nickname, String last4digits, boolean isDefault) {
        if (isDefault) {
            clearDefaultMethod(studentID);
        }
        String sql = "INSERT INTO BUE.SAVED_PAYMENT_METHODS (savedMethodID, studentID, methodType, nickname, last4digits, isDefault) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, studentID);
            ps.setString(3, methodType);
            ps.setString(4, nickname);
            ps.setString(5, last4digits);
            ps.setBoolean(6, isDefault);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error saving payment method: " + e.getMessage());
        }
    }

    public List<String[]> getSavedPaymentMethods(String studentID) {
        List<String[]> methods = new ArrayList<>();
        String sql = "SELECT savedMethodID, methodType, nickname, last4digits, isDefault FROM BUE.SAVED_PAYMENT_METHODS WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                methods.add(new String[]{
                    rs.getString("savedMethodID"),
                    rs.getString("methodType"),
                    rs.getString("nickname"),
                    rs.getString("last4digits"),
                    String.valueOf(rs.getBoolean("isDefault"))
                });
            }
        } catch (SQLException e) {
            System.err.println("Error fetching saved methods: " + e.getMessage());
        }
        return methods;
    }

    public void deleteSavedPaymentMethod(String savedMethodID) {
        String sql = "DELETE FROM BUE.SAVED_PAYMENT_METHODS WHERE savedMethodID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, savedMethodID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting saved method: " + e.getMessage());
        }
    }

    private void clearDefaultMethod(String studentID) {
        String sql = "UPDATE BUE.SAVED_PAYMENT_METHODS SET isDefault = false WHERE studentID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentID);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error clearing default method: " + e.getMessage());
        }
    }

    public String getStudentBalance(String studentID) {
        return "$5,250.00";
    }
}
