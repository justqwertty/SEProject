package me.sigma.entity;

import java.time.LocalDateTime;

public class Payment {
    private String paymentID;
    private String studentID;
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private String status;
    private String receiptNumber;
    private String transactionID;

    public Payment() {
        this.status = "PENDING";
        this.paymentDate = LocalDateTime.now();
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) throws PaymentException {
        if (amount <= 0) {
            throw new PaymentException("Payment amount must be positive");
        }
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }

    public boolean processPayment() {
        try {
            if (amount <= 0) {
                throw new PaymentException("Invalid payment amount");
            }
            this.status = "PROCESSING";
            return true;
        } catch (PaymentException e) {
            return false;
        }
    }

    public boolean validateFinancialAid() {
        return true;
    }

    public boolean connectToGateway(String method) {
        try {
            if (method == null || method.isEmpty()) {
                throw new PaymentException("Payment method cannot be empty");
            }
            return true;
        } catch (PaymentException e) {
            return false;
        }
    }

    public boolean confirmPayment() {
        try {
            if (transactionID == null) {
                throw new PaymentException("No transaction ID - authorization required first");
            }
            this.status = "COMPLETED";
            return true;
        } catch (PaymentException e) {
            this.status = "FAILED";
            return false;
        }
    }

    public Receipt generateReceipt() {
        Receipt receipt = new Receipt();
        receipt.setReceiptNumber(receiptNumber);
        receipt.setPaymentID(paymentID);
        receipt.setStudentID(studentID);
        receipt.setAmount(amount);
        receipt.setPaymentMethod(paymentMethod);
        return receipt;
    }
}
