package me.seproject.mavenproject1.boundary;

import me.seproject.mavenproject1.controller.PaymentController;
import me.seproject.mavenproject1.entity.Payment;
import me.seproject.mavenproject1.entity.Receipt;
import me.seproject.mavenproject1.entity.PaymentException;

public class TuitionUI extends javax.swing.JFrame {

    private PaymentController controller;
    private Payment currentPayment;

    public TuitionUI() {
        initComponents();
        controller = new PaymentController();
        currentPayment = new Payment();
        setSize(800, 600);
        setLocationRelativeTo(null);
        ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "LOGIN");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        subtitleLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        studentIdField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();
        paymentSelectionPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        amountLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        paymentMethodCombo = new javax.swing.JComboBox();
        proceedButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        paymentProcessingPanel = new javax.swing.JPanel();
        procTitleLabel = new javax.swing.JLabel();
        processingBar = new javax.swing.JProgressBar();
        processingStatus = new javax.swing.JLabel();
        confirmPaymentButton = new javax.swing.JButton();
        receiptPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        receiptArea = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        printButton = new javax.swing.JButton();
        emailButton = new javax.swing.JButton();
        doneButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tuition Payment System - UC-02");
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        titleLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Tuition Payment System");

        subtitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        subtitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtitleLabel.setText("Student Login");

        jLabel3.setText("Student ID:");

        jLabel4.setText("Password:");

        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        statusLabel.setForeground(new java.awt.Color(255, 0, 0));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel)
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(studentIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(27, 27, 27)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusLabel)
                    .addComponent(subtitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPanelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(titleLabel)
                .addGap(10, 10, 10)
                .addComponent(subtitleLabel)
                .addGap(40, 40, 40)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(studentIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statusLabel)
                .addGap(0, 29, Short.MAX_VALUE))
        );

        getContentPane().add(loginPanel, "LOGIN");

        headerLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        headerLabel.setText("Tuition Payment Details");

        jLabel6.setText("Outstanding Balance:");

        balanceLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        balanceLabel.setForeground(new java.awt.Color(192, 57, 43));
        balanceLabel.setText("$0.00");

        jLabel7.setText("Amount Due:");

        amountLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        amountLabel.setText("$0.00");

        jLabel8.setText("Select Payment Method:");

        paymentMethodCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Credit Card", "Debit Card", "Bank Transfer", "PayPal", "Financial Aid" }));
        paymentMethodCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentMethodComboActionPerformed(evt);
            }
        });

        proceedButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        proceedButton.setText("Proceed to Payment");
        proceedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentSelectionPanelLayout = new javax.swing.GroupLayout(paymentSelectionPanel);
        paymentSelectionPanel.setLayout(paymentSelectionPanelLayout);
        paymentSelectionPanelLayout.setHorizontalGroup(
            paymentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentSelectionPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(paymentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerLabel)
                    .addGroup(paymentSelectionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(balanceLabel))
                    .addGroup(paymentSelectionPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(44, 44, 44)
                        .addComponent(amountLabel))
                    .addComponent(jLabel8)
                    .addComponent(paymentMethodCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(paymentSelectionPanelLayout.createSequentialGroup()
                        .addComponent(proceedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 130, Short.MAX_VALUE))
        );
        paymentSelectionPanelLayout.setVerticalGroup(
            paymentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentSelectionPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(headerLabel)
                .addGap(30, 30, 30)
                .addGroup(paymentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(balanceLabel))
                .addGap(18, 18, 18)
                .addGroup(paymentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(amountLabel))
                .addGap(30, 30, 30)
                .addComponent(jLabel8)
                .addGap(10, 10, 10)
                .addComponent(paymentMethodCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(paymentSelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(proceedButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 34, Short.MAX_VALUE))
        );

        getContentPane().add(paymentSelectionPanel, "PAYMENT_SELECTION");

        procTitleLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        procTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        procTitleLabel.setText("Processing Payment");

        processingBar.setStringPainted(true);

        processingStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        processingStatus.setText("Connecting to payment gateway...");

        confirmPaymentButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        confirmPaymentButton.setText("Confirm Payment");
        confirmPaymentButton.setEnabled(false);
        confirmPaymentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmPaymentButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paymentProcessingPanelLayout = new javax.swing.GroupLayout(paymentProcessingPanel);
        paymentProcessingPanel.setLayout(paymentProcessingPanelLayout);
        paymentProcessingPanelLayout.setHorizontalGroup(
            paymentProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentProcessingPanelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addGroup(paymentProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(procTitleLabel)
                    .addComponent(processingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(processingStatus)
                    .addComponent(confirmPaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        paymentProcessingPanelLayout.setVerticalGroup(
            paymentProcessingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentProcessingPanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(procTitleLabel)
                .addGap(30, 30, 30)
                .addComponent(processingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(processingStatus)
                .addGap(30, 30, 30)
                .addComponent(confirmPaymentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );

        getContentPane().add(paymentProcessingPanel, "PAYMENT_PROCESSING");

        receiptPanel.setLayout(new java.awt.BorderLayout());

        receiptArea.setColumns(20);
        receiptArea.setEditable(false);
        receiptArea.setFont(new java.awt.Font("Courier New", 0, 12)); // NOI18N
        receiptArea.setLineWrap(true);
        receiptArea.setRows(5);
        jScrollPane1.setViewportView(receiptArea);

        receiptPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        printButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        printButton.setText("Print Receipt");
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        emailButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        emailButton.setText("Email Receipt");
        emailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailButtonActionPerformed(evt);
            }
        });

        doneButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(emailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(doneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        receiptPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);

        getContentPane().add(receiptPanel, "RECEIPT");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        handleLogin();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void paymentMethodComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentMethodComboActionPerformed
        checkFinancialAidOption();
    }//GEN-LAST:event_paymentMethodComboActionPerformed

    private void proceedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proceedButtonActionPerformed
        proceedToPayment();
    }//GEN-LAST:event_proceedButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        cancelPayment();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmPaymentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmPaymentButtonActionPerformed
        confirmPayment();
    }//GEN-LAST:event_confirmPaymentButtonActionPerformed

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printButtonActionPerformed
        printReceipt();
    }//GEN-LAST:event_printButtonActionPerformed

    private void emailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailButtonActionPerformed
        emailReceipt();
    }//GEN-LAST:event_emailButtonActionPerformed

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        closeApplication();
    }//GEN-LAST:event_doneButtonActionPerformed

    private void handleLogin() {
        String studentId = studentIdField.getText().trim();
        String password = new String(passwordField.getPassword());
        if (studentId.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter both Student ID and Password");
            return;
        }
        if (controller.validateCredentials(studentId)) {
            controller.computeFees(studentId);
            balanceLabel.setText("$5,250.00");
            currentPayment.setStudentID(studentId);
            ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "PAYMENT_SELECTION");
        } else {
            statusLabel.setText("Invalid credentials. Please try again.");
        }
    }

    private void checkFinancialAidOption() {
        String selected = (String) paymentMethodCombo.getSelectedItem();
        if ("Financial Aid".equals(selected)) {
            if (currentPayment.validateFinancialAid()) {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Financial Aid approved. Amount will be adjusted.",
                    "Financial Aid Status", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Financial Aid not available. Please select another method.",
                    "Financial Aid Status", javax.swing.JOptionPane.WARNING_MESSAGE);
                paymentMethodCombo.setSelectedIndex(0);
            }
        }
    }

    private void proceedToPayment() {
        String method = (String) paymentMethodCombo.getSelectedItem();
        currentPayment.setPaymentMethod(method);
        double amount = "Financial Aid".equals(method) ? 3500.00 : 5250.00;
        try {
            currentPayment.setAmount(amount);
        } catch (PaymentException e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Invalid amount: " + e.getMessage(),
                "Payment Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
        amountLabel.setText(String.format("$%.2f", amount));
        processingBar.setValue(0);
        processingStatus.setText("Connecting to payment gateway...");
        confirmPaymentButton.setEnabled(false);
        startProcessing();
        ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "PAYMENT_PROCESSING");
    }

    private void cancelPayment() {
        int result = javax.swing.JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel the payment?",
            "Confirm Cancel", javax.swing.JOptionPane.YES_NO_OPTION);
        if (result == javax.swing.JOptionPane.YES_OPTION) {
            controller.rollback();
            ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "LOGIN");
            studentIdField.setText("");
            passwordField.setText("");
            statusLabel.setText("");
        }
    }

    private void startProcessing() {
        javax.swing.Timer timer = new javax.swing.Timer(500, new java.awt.event.ActionListener() {
            int progress = 0;
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                progress += 10;
                processingBar.setValue(progress);
                if (progress == 30) {
                    processingStatus.setText("Authorizing transaction...");
                    controller.authorizeTransaction(currentPayment.getAmount(), currentPayment.getPaymentMethod());
                } else if (progress == 60) {
                    processingStatus.setText("Processing with gateway...");
                    currentPayment.processPayment();
                } else if (progress == 80) {
                    processingStatus.setText("Confirming payment...");
                    currentPayment.setTransactionID("TXN" + System.currentTimeMillis());
                } else if (progress >= 100) {
                    ((javax.swing.Timer) e.getSource()).stop();
                    processingStatus.setText("Payment authorized. Please confirm.");
                    confirmPaymentButton.setEnabled(true);
                }
            }
        });
        timer.start();
    }

    private void confirmPayment() {
        try {
            if (currentPayment.confirmPayment()) {
                controller.updateFinancialRecord();
                Receipt receipt = controller.generateReceipt(currentPayment);
                displayReceipt(receipt);
                ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "RECEIPT");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this,
                    "Payment confirmation failed. Please try again.",
                    "Payment Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                "Payment Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayReceipt(Receipt receipt) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String receiptNumber = receipt.getReceiptID() != null ? receipt.getReceiptID() : "RCP" + System.currentTimeMillis();
        receipt.setReceiptID(receiptNumber);
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("       TUITION PAYMENT RECEIPT\n");
        sb.append("========================================\n\n");
        sb.append("Receipt Number: ").append(receiptNumber).append("\n");
        sb.append("Transaction ID: ").append(receipt.getTransactionID() != null ? receipt.getTransactionID() : currentPayment.getTransactionID()).append("\n");
        sb.append("Student ID:     ").append(receipt.getStudentID()).append("\n");
        sb.append("Payment ID:     ").append(currentPayment.getPaymentID() != null ? currentPayment.getPaymentID() : "PAY" + System.currentTimeMillis()).append("\n");
        sb.append("\n----------------------------------------\n");
        sb.append("Amount Paid:    $").append(String.format("%.2f", receipt.getAmount())).append("\n");
        sb.append("Payment Method: ").append(receipt.getPaymentMethod()).append("\n");
        sb.append("Date/Time:      ").append(sdf.format(new java.util.Date())).append("\n");
        sb.append("\n========================================\n");
        sb.append("     PAYMENT COMPLETED SUCCESSFULLY\n");
        sb.append("========================================\n");
        receiptArea.setText(sb.toString());
    }

    private void printReceipt() {
        try {
            receiptArea.print();
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Print error: " + e.getMessage(),
                "Print Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }

    private void emailReceipt() {
        String email = javax.swing.JOptionPane.showInputDialog(this,
            "Enter email address to send receipt:",
            "Email Receipt", javax.swing.JOptionPane.QUESTION_MESSAGE);
        if (email != null && !email.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Receipt sent to: " + email,
                "Email Sent", javax.swing.JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void closeApplication() {
        int result = javax.swing.JOptionPane.showConfirmDialog(this,
            "Payment completed. Exit the application?",
            "Exit", javax.swing.JOptionPane.YES_NO_OPTION);
        if (result == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            ((java.awt.CardLayout) getContentPane().getLayout()).show(getContentPane(), "LOGIN");
            studentIdField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(TuitionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TuitionUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amountLabel;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmPaymentButton;
    private javax.swing.JButton doneButton;
    private javax.swing.JButton emailButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JComboBox paymentMethodCombo;
    private javax.swing.JPanel paymentProcessingPanel;
    private javax.swing.JPanel paymentSelectionPanel;
    private javax.swing.JButton printButton;
    private javax.swing.JLabel procTitleLabel;
    private javax.swing.JButton proceedButton;
    private javax.swing.JProgressBar processingBar;
    private javax.swing.JLabel processingStatus;
    private javax.swing.JTextArea receiptArea;
    private javax.swing.JPanel receiptPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField studentIdField;
    private javax.swing.JLabel subtitleLabel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
