package me.seproject.mavenproject1.controller;

import me.seproject.mavenproject1.entity.Payment;
import me.seproject.mavenproject1.entity.PaymentGateway;
import me.seproject.mavenproject1.entity.Receipt;
import me.seproject.mavenproject1.entity.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Controller for tuition payment operations.
 * Handles the business logic flow from the sequence diagram:
 * Student -> PaymentUI -> PaymentController -> PaymentGateway
 */
public class PaymentController {
    private String paymentMethod;
    private String transactionStatus;
    private int queueSize;
    private PaymentGateway gateway;
    private Map<String, Double> studentBalances;

    public PaymentController() {
        this.gateway = new PaymentGateway();
        this.studentBalances = new HashMap<>();
        // Initialize sample student balances
        studentBalances.put("S001", 5250.00);
        studentBalances.put("S002", 4800.00);
        studentBalances.put("S003", 6100.00);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    /**
     * Computes tuition fees for a student based on enrolled modules
     */
    public boolean computeFees(String studentID) {
        // In real implementation, would calculate from enrolled modules
        return studentBalances.containsKey(studentID);
    }

    /**
     * Gets the computed fee amount for a student
     */
    public double getFeeAmount(String studentID) {
        return studentBalances.getOrDefault(studentID, 0.0);
    }

    /**
     * Validates student credentials before allowing payment
     */
    public boolean validateCredentials(String studentID) {
        // In real implementation, would check against database
        return studentID != null && !studentID.isEmpty();
    }

    /**
     * Sets the payment method for the transaction
     */
    public void choosePaymentMethod(String method) {
        this.paymentMethod = method;
        this.transactionStatus = "METHOD_SELECTED";
    }

    /**
     * Processes payment queue (for batch processing)
     */
    public void processQueue() {
        queueSize = Math.max(0, queueSize - 1);
    }

    /**
     * Authorizes transaction with payment gateway
     */
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

    /**
     * Confirms the payment and updates transaction status
     */
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

    /**
     * Updates financial records after successful payment
     */
    public void updateFinancialRecord() {
        // In real implementation, would update database
        this.transactionStatus = "RECORD_UPDATED";
    }

    /**
     * Generates receipt for completed payment
     */
    public Receipt generateReceipt(Payment payment) {
        Receipt receipt = new Receipt();
        receipt.setReceiptID("RCP" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        receipt.setPaymentID(payment.getPaymentID());
        receipt.setStudentID(payment.getStudentID());
        receipt.setAmount((float) payment.getAmount());
        receipt.setPaymentMethod(payment.getPaymentMethod());
        receipt.setTransactionID(payment.getTransactionID());
        receipt.setPaymentDate(java.util.Date.from(payment.getPaymentDate().atZone(java.time.ZoneId.systemDefault()).toInstant()));
        return receipt;
    }

    /**
     * Rolls back transaction on failure
     */
    public void rollback() {
        this.paymentMethod = null;
        this.transactionStatus = "ROLLED_BACK";
    }

    /**
     * Gets student balance information
     */
    public String getStudentBalance(String studentID) {
        Double balance = studentBalances.get(studentID);
        if (balance != null) {
            return String.format("$%.2f", balance);
        }
        return "N/A";
    }
}
