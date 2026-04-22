# Payment System Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Implement a complete payment processing system with fee calculation, payment authorization, receipt generation, and Swing-based GUI.

**Architecture:** MVC pattern with controller handling business logic, boundary providing Swing GUI, and entities managing payment state. All methods include try-catch exception handling.

**Tech Stack:** Java 25, Swing for GUI, Maven for build.

---

## File Structure

**Files to Create:**
- `src/main/java/me/sigma/entity/PaymentException.java` - Custom exception class
- `src/main/java/me/sigma/entity/Receipt.java` - Receipt entity with details
- `src/main/java/me/sigma/boundary/PaymentPanel.java` - Main Swing payment panel
- `src/main/java/me/sigma/boundary/ReceiptPanel.java` - Receipt display panel
- `src/test/java/me/sigma/controller/PaymentControllerTest.java` - Unit tests
- `src/test/java/me/sigma/entity/ReceiptTest.java` - Entity tests

**Files to Modify:**
- `src/main/java/me/sigma/entity/Payment.java` - Add fields and validation
- `src/main/java/me/sigma/entity/PaymentGateway.java` - Implement payment processing
- `src/main/java/me/sigma/controller/PaymentController.java` - Implement all methods
- `src/main/java/me/sigma/boundary/PaymentUI.java` - Implement GUI methods

---

## Task 1: Payment Entity with Validation

**Files:**
- Modify: `src/main/java/me/sigma/entity/Payment.java`
- Create: `src/main/java/me/sigma/entity/PaymentException.java`
- Test: `src/test/java/me/sigma/entity/PaymentTest.java`

- [ ] **Step 1: Create custom exception class**

```java
package me.sigma.entity;

public class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
    
    public PaymentException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

- [ ] **Step 2: Run tests to verify compilation**

Run: `mvn test-compile`
Expected: BUILD SUCCESS

- [ ] **Step 3: Implement Payment entity**

```java
package me.sigma.entity;

import java.time.LocalDateTime;

public class Payment {
    private String paymentID;
    private String studentID;
    private double amount;
    private String paymentMethod;
    private String status; // "PENDING", "PROCESSING", "COMPLETED", "FAILED"
    private LocalDateTime timestamp;
    private String transactionID;

    public Payment() {
        this.status = "PENDING";
        this.timestamp = LocalDateTime.now();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
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
}
```

- [ ] **Step 4: Write unit test for Payment entity**

```java
package me.sigma.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    @Test
    public void testNewPaymentIsPending() {
        Payment payment = new Payment();
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    public void testValidAmount() throws PaymentException {
        Payment payment = new Payment();
        payment.setAmount(100.00);
        assertEquals(100.00, payment.getAmount());
    }

    @Test
    public void testNegativeAmountThrowsException() {
        Payment payment = new Payment();
        assertThrows(PaymentException.class, () -> {
            payment.setAmount(-50.00);
        });
    }

    @Test
    public void testZeroAmountThrowsException() {
        Payment payment = new Payment();
        assertThrows(PaymentException.class, () -> {
            payment.setAmount(0);
        });
    }

    @Test
    public void testIsCompleted() throws PaymentException {
        Payment payment = new Payment();
        payment.setStatus("COMPLETED");
        assertTrue(payment.isCompleted());
    }
}
```

- [ ] **Step 5: Run tests**

Run: `mvn test -Dtest=PaymentTest -v`
Expected: 5 tests PASS

- [ ] **Step 6: Commit**

```bash
git add src/main/java/me/sigma/entity/Payment.java
git add src/main/java/me/sigma/entity/PaymentException.java
git add src/test/java/me/sigma/entity/PaymentTest.java
git commit -m "feat: implement Payment entity with validation"
```

---

## Task 2: Receipt Entity

**Files:**
- Create: `src/main/java/me/sigma/entity/Receipt.java`
- Test: `src/test/java/me/sigma/entity/ReceiptTest.java`

- [ ] **Step 1: Implement Receipt entity**

```java
package me.sigma.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private String receiptID;
    private String paymentID;
    private String studentID;
    private String studentName;
    private double amount;
    private String paymentMethod;
    private LocalDateTime issueDate;
    private String description;

    public Receipt() {
        this.issueDate = LocalDateTime.now();
    }

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormattedAmount() {
        return String.format("$%.2f", amount);
    }

    public String getFormattedDate() {
        return issueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String generateReceiptText() {
        StringBuilder sb = new StringBuilder();
        sb.append("================================\n");
        sb.append("       PAYMENT RECEIPT\n");
        sb.append("================================\n");
        sb.append("Receipt ID: ").append(receiptID).append("\n");
        sb.append("Student ID: ").append(studentID).append("\n");
        sb.append("Student Name: ").append(studentName).append("\n");
        sb.append("Date: ").append(getFormattedDate()).append("\n");
        sb.append("--------------------------------\n");
        sb.append("Description: ").append(description).append("\n");
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Amount Paid: ").append(getFormattedAmount()).append("\n");
        sb.append("================================\n");
        sb.append("    Thank you for your payment!\n");
        sb.append("================================\n");
        return sb.toString();
    }
}
```

- [ ] **Step 2: Write unit test for Receipt entity**

```java
package me.sigma.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiptTest {

    @Test
    public void testNewReceiptHasIssueDate() {
        Receipt receipt = new Receipt();
        assertNotNull(receipt.getIssueDate());
    }

    @Test
    public void testFormattedAmount() {
        Receipt receipt = new Receipt();
        receipt.setAmount(123.45);
        assertEquals("$123.45", receipt.getFormattedAmount());
    }

    @Test
    public void testGenerateReceiptText() {
        Receipt receipt = new Receipt();
        receipt.setReceiptID("RCP001");
        receipt.setStudentID("S12345");
        receipt.setStudentName("John Doe");
        receipt.setAmount(500.00);
        receipt.setPaymentMethod("Credit Card");
        receipt.setDescription("Tuition Fee Payment");

        String text = receipt.generateReceiptText();
        
        assertTrue(text.contains("RCP001"));
        assertTrue(text.contains("S12345"));
        assertTrue(text.contains("John Doe"));
        assertTrue(text.contains("$500.00"));
        assertTrue(text.contains("Credit Card"));
    }
}
```

- [ ] **Step 3: Run tests**

Run: `mvn test -Dtest=ReceiptTest -v`
Expected: 3 tests PASS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/me/sigma/entity/Receipt.java
git add src/test/java/me/sigma/entity/ReceiptTest.java
git commit -m "feat: implement Receipt entity with formatted output"
```

---

## Task 3: PaymentGateway Implementation

**Files:**
- Modify: `src/main/java/me/sigma/entity/PaymentGateway.java`
- Test: `src/test/java/me/sigma/entity/PaymentGatewayTest.java`

- [ ] **Step 1: Read current PaymentGateway**

- [ ] **Step 2: Implement PaymentGateway**

```java
package me.sigma.entity;

import java.util.UUID;

public class PaymentGateway {
    private boolean connectionStatus;
    private String gatewayResponse;

    public boolean isConnected() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getGatewayResponse() {
        return gatewayResponse;
    }

    public void setGatewayResponse(String gatewayResponse) {
        this.gatewayResponse = gatewayResponse;
    }

    public boolean connect() {
        try {
            this.connectionStatus = true;
            this.gatewayResponse = "Connected to payment gateway";
            return true;
        } catch (Exception e) {
            this.connectionStatus = false;
            this.gatewayResponse = "Connection failed: " + e.getMessage();
            return false;
        }
    }

    public void disconnect() {
        try {
            this.connectionStatus = false;
            this.gatewayResponse = "Disconnected from payment gateway";
        } catch (Exception e) {
            this.gatewayResponse = "Disconnect error: " + e.getMessage();
        }
    }

    public boolean authorize(Payment payment) throws PaymentException {
        try {
            if (!connectionStatus) {
                throw new PaymentException("Not connected to payment gateway");
            }
            
            if (payment == null) {
                throw new PaymentException("Invalid payment object");
            }
            
            if (payment.getAmount() <= 0) {
                throw new PaymentException("Invalid payment amount");
            }

            // Simulate authorization
            String transactionID = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            payment.setTransactionID(transactionID);
            payment.setStatus("PROCESSING");
            
            this.gatewayResponse = "Authorization successful: " + transactionID;
            return true;
        } catch (PaymentException e) {
            this.gatewayResponse = "Authorization failed: " + e.getMessage();
            throw e;
        } catch (Exception e) {
            this.gatewayResponse = "Authorization error: " + e.getMessage();
            throw new PaymentException("Gateway error: " + e.getMessage(), e);
        }
    }

    public boolean completeTransaction(Payment payment) throws PaymentException {
        try {
            if (payment.getTransactionID() == null) {
                throw new PaymentException("No transaction ID - authorization required first");
            }

            // Simulate transaction completion
            payment.setStatus("COMPLETED");
            this.gatewayResponse = "Transaction completed: " + payment.getTransactionID();
            return true;
        } catch (PaymentException e) {
            this.gatewayResponse = "Transaction failed: " + e.getMessage();
            throw e;
        } catch (Exception e) {
            this.gatewayResponse = "Transaction error: " + e.getMessage();
            throw new PaymentException("Gateway error: " + e.getMessage(), e);
        }
    }

    public boolean cancelTransaction(Payment payment) {
        try {
            payment.setStatus("FAILED");
            this.gatewayResponse = "Transaction cancelled";
            return true;
        } catch (Exception e) {
            this.gatewayResponse = "Cancel error: " + e.getMessage();
            return false;
        }
    }
}
```

- [ ] **Step 3: Write unit test for PaymentGateway**

```java
package me.sigma.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentGatewayTest {

    @Test
    public void testConnect() {
        PaymentGateway gateway = new PaymentGateway();
        assertTrue(gateway.connect());
        assertTrue(gateway.isConnected());
    }

    @Test
    public void testDisconnect() {
        PaymentGateway gateway = new PaymentGateway();
        gateway.connect();
        gateway.disconnect();
        assertFalse(gateway.isConnected());
    }

    @Test
    public void testAuthorizeWithoutConnection() {
        PaymentGateway gateway = new PaymentGateway();
        Payment payment = new Payment();
        payment.setAmount(100.00);
        
        assertThrows(PaymentException.class, () -> {
            gateway.authorize(payment);
        });
    }

    @Test
    public void testAuthorizeWithConnection() throws PaymentException {
        PaymentGateway gateway = new PaymentGateway();
        gateway.connect();
        
        Payment payment = new Payment();
        payment.setAmount(100.00);
        
        assertTrue(gateway.authorize(payment));
        assertNotNull(payment.getTransactionID());
        assertEquals("PROCESSING", payment.getStatus());
    }

    @Test
    public void testCompleteTransaction() throws PaymentException {
        PaymentGateway gateway = new PaymentGateway();
        gateway.connect();
        
        Payment payment = new Payment();
        payment.setAmount(100.00);
        gateway.authorize(payment);
        
        assertTrue(gateway.completeTransaction(payment));
        assertEquals("COMPLETED", payment.getStatus());
    }

    @Test
    public void testCancelTransaction() {
        PaymentGateway gateway = new PaymentGateway();
        Payment payment = new Payment();
        
        assertTrue(gateway.cancelTransaction(payment));
        assertEquals("FAILED", payment.getStatus());
    }
}
```

- [ ] **Step 4: Run tests**

Run: `mvn test -Dtest=PaymentGatewayTest -v`
Expected: 6 tests PASS

- [ ] **Step 5: Commit**

```bash
git add src/main/java/me/sigma/entity/PaymentGateway.java
git add src/test/java/me/sigma/entity/PaymentGatewayTest.java
git commit -m "feat: implement PaymentGateway with authorization flow"
```

---

## Task 4: PaymentController Implementation

**Files:**
- Modify: `src/main/java/me/sigma/controller/PaymentController.java`
- Test: `src/test/java/me/sigma/controller/PaymentControllerTest.java`

- [ ] **Step 1: Implement PaymentController**

```java
package me.sigma.controller;

import me.sigma.entity.*;
import java.util.*;

public class PaymentController {
    private String paymentMethod;
    private String transactionStatus;
    private int queueSize;
    private PaymentGateway gateway;
    private Map<String, Double> feeStructure;
    private List<Payment> paymentQueue;

    public PaymentController() {
        this.gateway = new PaymentGateway();
        this.feeStructure = new HashMap<>();
        this.paymentQueue = new ArrayList<>();
        initializeFeeStructure();
    }

    private void initializeFeeStructure() {
        feeStructure.put("TUITION", 5000.00);
        feeStructure.put("LAB", 500.00);
        feeStructure.put("LIBRARY", 200.00);
        feeStructure.put("ACTIVITY", 150.00);
        feeStructure.put("HOUSING", 3000.00);
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

    public double computeFees(String studentID) throws PaymentException {
        try {
            if (studentID == null || studentID.isEmpty()) {
                throw new PaymentException("Invalid student ID");
            }
            
            // Simulate fee calculation based on enrolled modules
            double totalFees = 0.0;
            
            // In real implementation, fetch from database
            // For now, return standard tuition + lab fees
            totalFees += feeStructure.getOrDefault("TUITION", 0.0);
            totalFees += feeStructure.getOrDefault("LAB", 0.0);
            
            return totalFees;
        } catch (PaymentException e) {
            throw e;
        } catch (Exception e) {
            throw new PaymentException("Fee calculation error: " + e.getMessage(), e);
        }
    }

    public boolean validateCredentials(String studentID) throws PaymentException {
        try {
            if (studentID == null || studentID.isEmpty()) {
                throw new PaymentException("Invalid student ID");
            }
            
            // Simulate credential validation
            // In real implementation, check against database
            return studentID.matches("S\\d+");
        } catch (PaymentException e) {
            throw e;
        } catch (Exception e) {
            throw new PaymentException("Credential validation error: " + e.getMessage(), e);
        }
    }

    public void choosePaymentMethod(String method) throws PaymentException {
        try {
            if (method == null || method.isEmpty()) {
                throw new PaymentException("Payment method cannot be empty");
            }
            
            List<String> validMethods = Arrays.asList("CREDIT_CARD", "DEBIT_CARD", "BANK_TRANSFER", "CASH");
            if (!validMethods.contains(method.toUpperCase())) {
                throw new PaymentException("Invalid payment method: " + method);
            }
            
            this.paymentMethod = method.toUpperCase();
        } catch (PaymentException e) {
            throw e;
        } catch (Exception e) {
            throw new PaymentException("Payment method selection error: " + e.getMessage(), e);
        }
    }

    public void processQueue() {
        try {
            this.queueSize = paymentQueue.size();
        } catch (Exception e) {
            this.transactionStatus = "Queue processing error: " + e.getMessage();
        }
    }

    public Receipt authorizeTransaction(double amount, String studentID, String studentName) throws PaymentException {
        try {
            if (!gateway.isConnected()) {
                gateway.connect();
            }

            Payment payment = new Payment();
            payment.setStudentID(studentID);
            payment.setAmount(amount);
            payment.setPaymentMethod(paymentMethod);

            gateway.authorize(payment);

            this.transactionStatus = "AUTHORIZED";

            // Generate receipt
            Receipt receipt = new Receipt();
            receipt.setReceiptID("RCP" + System.currentTimeMillis());
            receipt.setPaymentID(payment.getPaymentID());
            receipt.setStudentID(studentID);
            receipt.setStudentName(studentName);
            receipt.setAmount(amount);
            receipt.setPaymentMethod(paymentMethod);
            receipt.setDescription("Payment Authorization");

            return receipt;
        } catch (PaymentException e) {
            this.transactionStatus = "FAILED";
            throw e;
        } catch (Exception e) {
            this.transactionStatus = "ERROR";
            throw new PaymentException("Transaction authorization error: " + e.getMessage(), e);
        }
    }

    public boolean confirmPayment(Receipt receipt) throws PaymentException {
        try {
            if (receipt == null) {
                throw new PaymentException("Invalid receipt");
            }

            boolean success = gateway.completeTransaction(new Payment());
            
            if (success) {
                this.transactionStatus = "COMPLETED";
            } else {
                this.transactionStatus = "FAILED";
            }
            
            return success;
        } catch (PaymentException e) {
            this.transactionStatus = "FAILED";
            throw e;
        } catch (Exception e) {
            this.transactionStatus = "ERROR";
            throw new PaymentException("Payment confirmation error: " + e.getMessage(), e);
        }
    }

    public void updateFinancialRecord(Receipt receipt) throws PaymentException {
        try {
            if (receipt == null) {
                throw new PaymentException("Invalid receipt");
            }
            // In real implementation: update database
            System.out.println("Financial record updated for: " + receipt.getReceiptID());
        } catch (PaymentException e) {
            throw e;
        } catch (Exception e) {
            throw new PaymentException("Record update error: " + e.getMessage(), e);
        }
    }

    public Receipt generateReceipt(Payment payment, String studentName) throws PaymentException {
        try {
            if (payment == null) {
                throw new PaymentException("Invalid payment");
            }

            Receipt receipt = new Receipt();
            receipt.setReceiptID("RCP" + System.currentTimeMillis());
            receipt.setPaymentID(payment.getPaymentID());
            receipt.setStudentID(payment.getStudentID());
            receipt.setStudentName(studentName);
            receipt.setAmount(payment.getAmount());
            receipt.setPaymentMethod(payment.getPaymentMethod());
            receipt.setDescription("Payment Receipt");

            return receipt;
        } catch (Exception e) {
            throw new PaymentException("Receipt generation error: " + e.getMessage(), e);
        }
    }

    public void rollback(Payment payment) {
        try {
            if (payment != null) {
                gateway.cancelTransaction(payment);
                this.transactionStatus = "ROLLED_BACK";
            }
        } catch (Exception e) {
            this.transactionStatus = "ROLLBACK_ERROR: " + e.getMessage();
        }
    }

    public Map<String, Double> getFeeStructure() {
        return new HashMap<>(feeStructure);
    }
}
```

- [ ] **Step 2: Write unit test for PaymentController**

```java
package me.sigma.controller;

import me.sigma.entity.PaymentException;
import me.sigma.entity.Receipt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentControllerTest {

    @Test
    public void testComputeFees() throws PaymentException {
        PaymentController controller = new PaymentController();
        double fees = controller.computeFees("S12345");
        assertEquals(5500.00, fees); // TUITION + LAB
    }

    @Test
    public void testComputeFeesWithInvalidStudentID() {
        PaymentController controller = new PaymentController();
        assertThrows(PaymentException.class, () -> {
            controller.computeFees("");
        });
    }

    @Test
    public void testValidateCredentialsValid() throws PaymentException {
        PaymentController controller = new PaymentController();
        assertTrue(controller.validateCredentials("S12345"));
    }

    @Test
    public void testValidateCredentialsInvalid() throws PaymentException {
        PaymentController controller = new PaymentController();
        assertFalse(controller.validateCredentials("INVALID"));
    }

    @Test
    public void testChoosePaymentMethodValid() throws PaymentException {
        PaymentController controller = new PaymentController();
        controller.choosePaymentMethod("CREDIT_CARD");
        assertEquals("CREDIT_CARD", controller.getPaymentMethod());
    }

    @Test
    public void testChoosePaymentMethodInvalid() {
        PaymentController controller = new PaymentController();
        assertThrows(PaymentException.class, () -> {
            controller.choosePaymentMethod("INVALID_METHOD");
        });
    }

    @Test
    public void testAuthorizeTransaction() throws PaymentException {
        PaymentController controller = new PaymentController();
        controller.choosePaymentMethod("CREDIT_CARD");
        
        Receipt receipt = controller.authorizeTransaction(100.00, "S12345", "John Doe");
        
        assertNotNull(receipt);
        assertEquals("S12345", receipt.getStudentID());
        assertEquals(100.00, receipt.getAmount());
        assertEquals("AUTHORIZED", controller.getTransactionStatus());
    }

    @Test
    public void testGetFeeStructure() {
        PaymentController controller = new PaymentController();
        Map<String, Double> fees = controller.getFeeStructure();
        
        assertTrue(fees.containsKey("TUITION"));
        assertTrue(fees.containsKey("LAB"));
        assertEquals(5000.00, fees.get("TUITION"));
    }
}
```

- [ ] **Step 3: Run tests**

Run: `mvn test -Dtest=PaymentControllerTest -v`
Expected: 8 tests PASS

- [ ] **Step 4: Commit**

```bash
git add src/main/java/me/sigma/controller/PaymentController.java
git add src/test/java/me/sigma/controller/PaymentControllerTest.java
git commit -m "feat: implement PaymentController with fee calculation and payment flow"
```

---

## Task 5: PaymentUI Swing GUI

**Files:**
- Modify: `src/main/java/me/sigma/boundary/PaymentUI.java`
- Create: `src/main/java/me/sigma/boundary/PaymentPanel.java`
- Create: `src/main/java/me/sigma/boundary/ReceiptPanel.java`

- [ ] **Step 1: Create ReceiptPanel for displaying receipts**

```java
package me.sigma.boundary;

import me.sigma.entity.Receipt;

import javax.swing.*;
import java.awt.*;

public class ReceiptPanel extends JPanel {
    
    public ReceiptPanel(Receipt receipt) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        contentPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        
        // Header
        JLabel headerLabel = new JLabel("PAYMENT RECEIPT", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 0;
        contentPanel.add(headerLabel, gbc);
        
        // Separator
        gbc.gridy = 1;
        contentPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        
        // Receipt details
        gbc.gridy = 2;
        contentPanel.add(createDetailLabel("Receipt ID: " + receipt.getReceiptID()), gbc);
        
        gbc.gridy = 3;
        contentPanel.add(createDetailLabel("Student ID: " + receipt.getStudentID()), gbc);
        
        gbc.gridy = 4;
        contentPanel.add(createDetailLabel("Student Name: " + receipt.getStudentName()), gbc);
        
        gbc.gridy = 5;
        contentPanel.add(createDetailLabel("Date: " + receipt.getFormattedDate()), gbc);
        
        gbc.gridy = 6;
        contentPanel.add(createDetailLabel("Description: " + receipt.getDescription()), gbc);
        
        gbc.gridy = 7;
        contentPanel.add(createDetailLabel("Payment Method: " + receipt.getPaymentMethod()), gbc);
        
        // Amount (larger font)
        gbc.gridy = 8;
        JLabel amountLabel = new JLabel("Amount Paid: " + receipt.getFormattedAmount(), SwingConstants.CENTER);
        amountLabel.setFont(new Font("Arial", Font.BOLD, 16));
        amountLabel.setForeground(new Color(0, 128, 0));
        contentPanel.add(amountLabel, gbc);
        
        // Separator
        gbc.gridy = 9;
        contentPanel.add(new JSeparator(SwingConstants.HORIZONTAL), gbc);
        
        // Footer
        JLabel footerLabel = new JLabel("Thank you for your payment!", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        gbc.gridy = 10;
        contentPanel.add(footerLabel, gbc);
        
        add(contentPanel, BorderLayout.CENTER);
    }
    
    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }
}
```

- [ ] **Step 2: Create PaymentPanel main GUI**

```java
package me.sigma.boundary;

import me.sigma.controller.PaymentController;
import me.sigma.entity.PaymentException;
import me.sigma.entity.Receipt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPanel extends JPanel {
    
    private PaymentController controller;
    private JTextField studentIdField;
    private JTextField studentNameField;
    private JComboBox<String> paymentMethodCombo;
    private JLabel feeLabel;
    private JTextArea statusArea;
    private JButton payButton;
    private Receipt currentReceipt;
    
    public PaymentPanel() {
        controller = new PaymentController();
        currentReceipt = null;
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel titleLabel = new JLabel("Student Payment System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        
        // Main form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Student ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        formPanel.add(new JLabel("Student ID:"), gbc);
        
        gbc.gridx = 1;
        studentIdField = new JTextField(15);
        formPanel.add(studentIdField, gbc);
        
        // Student Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Student Name:"), gbc);
        
        gbc.gridx = 1;
        studentNameField = new JTextField(15);
        formPanel.add(studentNameField, gbc);
        
        // Payment Method
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Payment Method:"), gbc);
        
        gbc.gridx = 1;
        String[] methods = {"CREDIT_CARD", "DEBIT_CARD", "BANK_TRANSFER", "CASH"};
        paymentMethodCombo = new JComboBox<>(methods);
        formPanel.add(paymentMethodCombo, gbc);
        
        // Calculate Fees Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton calcFeesButton = new JButton("Calculate Fees");
        calcFeesButton.addActionListener(new CalcFeesListener());
        formPanel.add(calcFeesButton, gbc);
        
        // Fee Display
        gbc.gridx = 1;
        feeLabel = new JLabel("Fees: $0.00");
        feeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(feeLabel, gbc);
        
        // Status Area
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        statusArea = new JTextArea(3, 30);
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);
        statusArea.setBorder(BorderFactory.createTitledBorder("Status"));
        formPanel.add(new JScrollPane(statusArea), gbc);
        
        // Pay Button
        gbc.gridy = 5;
        payButton = new JButton("Process Payment");
        payButton.addActionListener(new PayListener());
        payButton.setEnabled(false);
        formPanel.add(payButton, gbc);
        
        add(formPanel, BorderLayout.CENTER);
        
        // Receipt panel (initially empty)
        JPanel receiptContainer = new JPanel(new BorderLayout());
        receiptContainer.setBorder(BorderFactory.createTitledBorder("Receipt"));
        receiptContainer.setPreferredSize(new Dimension(400, 300));
        receiptContainer.add(new JLabel("Receipt will appear here", SwingConstants.CENTER));
        add(receiptContainer, BorderLayout.SOUTH);
    }
    
    private class CalcFeesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String studentId = studentIdField.getText().trim();
                String studentName = studentNameField.getText().trim();
                
                if (studentId.isEmpty() || studentName.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        PaymentPanel.this,
                        "Please enter both Student ID and Name",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                // Validate credentials
                if (!controller.validateCredentials(studentId)) {
                    throw new PaymentException("Invalid student ID format");
                }
                
                // Calculate fees
                double fees = controller.computeFees(studentId);
                feeLabel.setText("Fees: $" + String.format("%.2f", fees));
                
                // Set payment method
                String method = (String) paymentMethodCombo.getSelectedItem();
                controller.choosePaymentMethod(method);
                
                statusArea.setText("Fees calculated successfully: $" + String.format("%.2f", fees));
                payButton.setEnabled(true);
                
            } catch (PaymentException ex) {
                JOptionPane.showMessageDialog(
                    PaymentPanel.this,
                    "Error: " + ex.getMessage(),
                    "Payment Error",
                    JOptionPane.ERROR_MESSAGE
                );
                statusArea.setText("Error: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    PaymentPanel.this,
                    "Unexpected error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                statusArea.setText("Unexpected error: " + ex.getMessage());
            }
        }
    }
    
    private class PayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String studentId = studentIdField.getText().trim();
                String studentName = studentNameField.getText().trim();
                double fees = controller.computeFees(studentId);
                
                // Authorize transaction
                currentReceipt = controller.authorizeTransaction(fees, studentId, studentName);
                
                // Confirm payment
                boolean success = controller.confirmPayment(currentReceipt);
                
                if (success) {
                    // Update financial record
                    controller.updateFinancialRecord(currentReceipt);
                    
                    statusArea.setText("Payment successful! Receipt ID: " + currentReceipt.getReceiptID());
                    
                    // Show receipt in bottom panel
                    JPanel receiptContainer = (JPanel) getParent();
                    receiptContainer.removeAll();
                    receiptContainer.add(new ReceiptPanel(currentReceipt), BorderLayout.CENTER);
                    receiptContainer.revalidate();
                    receiptContainer.repaint();
                    
                    JOptionPane.showMessageDialog(
                        PaymentPanel.this,
                        "Payment completed successfully!\nReceipt ID: " + currentReceipt.getReceiptID(),
                        "Payment Successful",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    
                    payButton.setEnabled(false);
                } else {
                    throw new PaymentException("Payment confirmation failed");
                }
                
            } catch (PaymentException ex) {
                JOptionPane.showMessageDialog(
                    PaymentPanel.this,
                    "Payment failed: " + ex.getMessage(),
                    "Payment Error",
                    JOptionPane.ERROR_MESSAGE
                );
                statusArea.setText("Payment failed: " + ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                    PaymentPanel.this,
                    "Unexpected error: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                statusArea.setText("Unexpected error: " + ex.getMessage());
            }
        }
    }
}
```

- [ ] **Step 3: Update PaymentUI to launch the application**

```java
package me.sigma.boundary;

import javax.swing.*;

public class PaymentUI {
    
    public void accessPaymentSection() {
        try {
            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Student Payment System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 700);
                frame.setLocationRelativeTo(null);
                
                PaymentPanel paymentPanel = new PaymentPanel();
                frame.setContentPane(paymentPanel);
                
                frame.setVisible(true);
            });
        } catch (Exception e) {
            alertUser();
            System.err.println("Error launching payment section: " + e.getMessage());
        }
    }
    
    public void processPayment() {
        accessPaymentSection();
    }
    
    public void alertUser() {
        JOptionPane.showMessageDialog(
            null,
            "An error occurred. Please try again.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    public void blockAccess() {
        JOptionPane.showMessageDialog(
            null,
            "Access denied. Please contact administrator.",
            "Access Denied",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
```

- [ ] **Step 4: Create main application launcher**

```java
package me.sigma;

import me.sigma.boundary.PaymentUI;

import javax.swing.*;

public class PaymentApp {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        PaymentUI paymentUI = new PaymentUI();
        paymentUI.accessPaymentSection();
    }
}
```

- [ ] **Step 5: Commit**

```bash
git add src/main/java/me/sigma/boundary/PaymentUI.java
git add src/main/java/me/sigma/boundary/PaymentPanel.java
git add src/main/java/me/sigma/boundary/ReceiptPanel.java
git add src/main/java/me/sigma/PaymentApp.java
git commit -m "feat: implement PaymentUI Swing GUI with receipt display"
```

---

## Task 6: Integration Testing

**Files:**
- Create: `src/test/java/me/sigma/integration/PaymentIntegrationTest.java`

- [ ] **Step 1: Write integration test**

```java
package me.sigma.integration;

import me.sigma.controller.PaymentController;
import me.sigma.entity.PaymentException;
import me.sigma.entity.Receipt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentIntegrationTest {

    @Test
    public void testFullPaymentFlow() throws PaymentException {
        PaymentController controller = new PaymentController();
        
        // Step 1: Validate student
        String studentId = "S12345";
        String studentName = "John Doe";
        assertTrue(controller.validateCredentials(studentId));
        
        // Step 2: Calculate fees
        double fees = controller.computeFees(studentId);
        assertEquals(5500.00, fees);
        
        // Step 3: Choose payment method
        controller.choosePaymentMethod("CREDIT_CARD");
        assertEquals("CREDIT_CARD", controller.getPaymentMethod());
        
        // Step 4: Authorize transaction
        Receipt receipt = controller.authorizeTransaction(fees, studentId, studentName);
        assertNotNull(receipt);
        assertEquals("AUTHORIZED", controller.getTransactionStatus());
        
        // Step 5: Confirm payment
        boolean success = controller.confirmPayment(receipt);
        assertTrue(success);
        assertEquals("COMPLETED", controller.getTransactionStatus());
        
        // Step 6: Update records
        controller.updateFinancialRecord(receipt);
        
        // Verify receipt contents
        assertEquals(studentId, receipt.getStudentID());
        assertEquals(studentName, receipt.getStudentName());
        assertEquals(fees, receipt.getAmount());
        assertEquals("CREDIT_CARD", receipt.getPaymentMethod());
    }

    @Test
    public void testPaymentWithInvalidStudent() {
        PaymentController controller = new PaymentController();
        
        assertThrows(PaymentException.class, () -> {
            controller.computeFees("INVALID");
        });
    }

    @Test
    public void testPaymentWithInvalidMethod() {
        PaymentController controller = new PaymentController();
        
        assertThrows(PaymentException.class, () -> {
            controller.choosePaymentMethod("CRYPTOCURRENCY");
        });
    }
}
```

- [ ] **Step 2: Run all payment tests**

Run: `mvn test -Dtest=PaymentIntegrationTest -v`
Expected: 3 tests PASS

- [ ] **Step 3: Run full test suite**

Run: `mvn test -v`
Expected: All tests PASS

- [ ] **Step 4: Commit**

```bash
git add src/test/java/me/sigma/integration/PaymentIntegrationTest.java
git commit -m "test: add integration tests for payment flow"
```

---

## Task 7: Update Main Application

**Files:**
- Modify: `src/main/java/me/sigma/Main.java`

- [ ] **Step 1: Update Main to launch PaymentUI**

```java
package me.sigma;

import me.sigma.boundary.PaymentUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Set system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Launch payment system
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Student Record System - Payment");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 700);
            frame.setLocationRelativeTo(null);
            
            PaymentUI paymentUI = new PaymentUI();
            paymentUI.accessPaymentSection();
            
            // Note: PaymentUI creates its own content, so we just show the frame
            frame.setVisible(true);
        });
    }
}
```

- [ ] **Step 2: Run application**

Run: `mvn exec:java -Dexec.mainClass="me.sigma.Main"`
Expected: GUI window opens with payment form

- [ ] **Step 3: Commit**

```bash
git add src/main/java/me/sigma/Main.java
git commit -m "feat: update Main to launch PaymentUI"
```

---

## Summary

**Total Tests:** 25+
**Total Commits:** 7

**Files Created:**
- `PaymentException.java` - Custom exception
- `Receipt.java` - Receipt entity
- `PaymentPanel.java` - Main payment GUI
- `ReceiptPanel.java` - Receipt display
- `PaymentControllerTest.java` - Controller tests
- `ReceiptTest.java` - Entity tests
- `PaymentGatewayTest.java` - Gateway tests
- `PaymentIntegrationTest.java` - Integration tests

**Files Modified:**
- `Payment.java` - Added validation
- `PaymentGateway.java` - Full implementation
- `PaymentController.java` - Full implementation
- `PaymentUI.java` - GUI implementation
- `Main.java` - Application entry point

---

Plan complete and saved to `docs/superpowers/plans/2026-04-20-payment-system.md`. Two execution options:

**1. Subagent-Driven (recommended)** - I dispatch a fresh subagent per task, review between tasks, fast iteration

**2. Inline Execution** - Execute tasks in this session using executing-plans, batch execution with checkpoints

**Which approach?**
