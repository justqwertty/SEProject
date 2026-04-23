package me.seproject.mavenproject1.entity;

import java.util.Map;
import java.util.UUID;

/**
 * Simulates external payment gateway integration.
 * Supports multiple payment providers as shown in class diagram.
 */
public class PaymentGateway {
    private String gatewayName;
    private String transactionID;

    public PaymentGateway() {
        this.gatewayName = "DefaultGateway";
    }

    public PaymentGateway(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getGatewayName() {
        return gatewayName;
    }

    public void setGatewayName(String gatewayName) {
        this.gatewayName = gatewayName;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * Initiates payment through the gateway
     * Simulates connection to external payment processor
     */
    public boolean makePayment(float amount, Map<String, Object> paymentDetails) {
        try {
            // Validate payment details
            if (amount <= 0) {
                return false;
            }

            String method = (String) paymentDetails.get("method");
            if (method == null || method.isEmpty()) {
                return false;
            }

            // Generate transaction ID
            this.transactionID = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // Simulate gateway processing (in real app, would call external API)
            return simulateGatewayProcessing(amount, method);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Confirms a previously authorized transaction
     */
    public boolean confirmPayment(String transactionID) {
        if (transactionID == null || transactionID.isEmpty()) {
            return false;
        }

        // In real implementation, would verify with gateway
        this.transactionID = transactionID;
        return true;
    }

    /**
     * Simulates different gateway behaviors based on payment method
     */
    private boolean simulateGatewayProcessing(float amount, String method) {
        // Simulate processing time
        try {
            Thread.sleep(100); // Small delay to simulate network call
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }

        // All valid methods succeed in simulation
        switch (method) {
            case "Credit Card":
            case "Debit Card":
            case "Bank Transfer":
            case "PayPal":
            case "Financial Aid":
                return true;
            default:
                return false;
        }
    }

    /**
     * Refunds a transaction
     */
    public boolean refund(String transactionID, float amount) {
        // In real implementation, would process refund through gateway
        return transactionID != null && amount > 0;
    }

    /**
     * Checks transaction status
     */
    public String checkStatus(String transactionID) {
        if (transactionID == null) {
            return "UNKNOWN";
        }
        return "COMPLETED";
    }
}
