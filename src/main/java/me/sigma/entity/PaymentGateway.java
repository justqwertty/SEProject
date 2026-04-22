package me.sigma.entity;

import java.util.Map;

public class PaymentGateway {
    private String gatewayName;
    private String transactionID;

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

    public boolean makePayment(float amount, Map<String, Object> paymentDetails) {
        return false;
    }

    public boolean confirmPayment(String transactionID) {
        return false;
    }
}
