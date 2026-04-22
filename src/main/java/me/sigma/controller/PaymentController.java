package me.sigma.controller;

import me.sigma.entity.Payment;
import me.sigma.entity.Receipt;

public class PaymentController {
    private String paymentMethod;
    private String transactionStatus;
    private int queueSize;

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

    public boolean computeFees(String studentID) {
        return false;
    }

    public boolean validateCredentials(String studentID) {
        return false;
    }

    public void choosePaymentMethod(String method) {
    }

    public void processQueue() {
    }

    public void authorizeTransaction(double amount, String method) {
    }

    public boolean confirmPayment() {
        return false;
    }

    public void updateFinancialRecord() {
    }

    public void generateReceipt() {
    }

    public void rollback() {
    }
}
