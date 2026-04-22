package me.sigma.entity;

import java.util.Date;

public class Receipt {
    private String receiptID;
    private String paymentID;
    private String studentID;
    private float amount;
    private Date paymentDate;
    private String paymentMethod;
    private String transactionID;

    public String getReceiptID() {
        return receiptID;
    }

    public void setReceiptID(String receiptID) {
        this.receiptID = receiptID;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String generateDigitalReceipt() {
        return null;
    }

    public boolean sendReceiptByEmail(String email) {
        return false;
    }

    public String displayReceipt() {
        return null;
    }

    public java.io.File exportReceipt() {
        return null;
    }
}
