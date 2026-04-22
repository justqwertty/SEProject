package me.sigma.boundary;

import me.sigma.controller.PaymentController;
import me.sigma.entity.Payment;
import me.sigma.entity.Receipt;
import me.sigma.entity.PaymentException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TuitionUI extends JFrame {

    // Bound fields matching TuitionUI.form bindings
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JTextField studentIdField;
    private JPasswordField passwordField;
    private JLabel statusLabel;
    private JButton loginButton;
    private JPanel paymentSelectionPanel;
    private JLabel balanceLabel;
    private JLabel amountLabel;
    private JComboBox<String> paymentMethodCombo;
    private JButton proceedButton;
    private JButton cancelButton;
    private JPanel paymentProcessingPanel;
    private JProgressBar processingBar;
    private JLabel processingStatus;
    private JButton confirmPaymentButton;
    private JPanel receiptPanel;
    private JTextArea receiptArea;
    private JButton printButton;
    private JButton emailButton;
    private JButton doneButton;

    private PaymentController controller;
    private Payment currentPayment;
    private CardLayout cardLayout;

    private static final String[] PAYMENT_METHODS = {
        "Credit Card", "Debit Card", "Bank Transfer", "PayPal", "Financial Aid"
    };

    public TuitionUI() {
        controller = new PaymentController();
        currentPayment = new Payment();
        initComponents();
        initFrame();
        initEventHandlers();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createPaymentSelectionPanel(), "PAYMENT_SELECTION");
        mainPanel.add(createPaymentProcessingPanel(), "PAYMENT_PROCESSING");
        mainPanel.add(createReceiptPanel(), "RECEIPT");
    }

    private void initFrame() {
        setTitle("Tuition Payment System - UC-02");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setContentPane(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    private void initEventHandlers() {
        loginButton.addActionListener(e -> handleLogin());
        proceedButton.addActionListener(e -> proceedToPayment());
        cancelButton.addActionListener(e -> cancelPayment());
        confirmPaymentButton.addActionListener(e -> confirmPayment());
        paymentMethodCombo.addActionListener(e -> checkFinancialAidOption());
        printButton.addActionListener(e -> printReceipt());
        emailButton.addActionListener(e -> emailReceipt());
        doneButton.addActionListener(e -> closeApplication());
    }

    private JPanel createLoginPanel() {
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Tuition Payment System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(titleLabel, gbc);

        JLabel subtitleLabel = new JLabel("Student Login");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        loginPanel.add(subtitleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 2; gbc.gridx = 0;
        loginPanel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        studentIdField = new JTextField(15);
        loginPanel.add(studentIdField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        loginPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        loginPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.GRAY);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginPanel.add(loginButton, gbc);

        gbc.gridy = 5;
        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginPanel.add(statusLabel, gbc);

        return loginPanel;
    }

    private JPanel createPaymentSelectionPanel() {
        paymentSelectionPanel = new JPanel(new GridBagLayout());
        paymentSelectionPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel headerLabel = new JLabel("Tuition Payment Details");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        paymentSelectionPanel.add(headerLabel, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1; gbc.gridx = 0;
        paymentSelectionPanel.add(new JLabel("Outstanding Balance:"), gbc);

        gbc.gridx = 1;
        balanceLabel = new JLabel("$0.00");
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(192, 57, 43));
        paymentSelectionPanel.add(balanceLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        paymentSelectionPanel.add(new JLabel("Amount Due:"), gbc);

        gbc.gridx = 1;
        amountLabel = new JLabel("$0.00");
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        paymentSelectionPanel.add(amountLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        paymentSelectionPanel.add(new JLabel("Select Payment Method:"), gbc);

        gbc.gridy = 4;
        paymentMethodCombo = new JComboBox<>(PAYMENT_METHODS);
        paymentMethodCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        paymentSelectionPanel.add(paymentMethodCombo, gbc);

        gbc.gridy = 5;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        proceedButton = new JButton("Proceed to Payment");
        proceedButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        proceedButton.setBackground(new Color(39, 174, 96));
        proceedButton.setForeground(Color.GRAY);
        proceedButton.setPreferredSize(new Dimension(180, 40));
        buttonPanel.add(proceedButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.GRAY);
        cancelButton.setPreferredSize(new Dimension(120, 40));
        buttonPanel.add(cancelButton);

        paymentSelectionPanel.add(buttonPanel, gbc);

        return paymentSelectionPanel;
    }

    private JPanel createPaymentProcessingPanel() {
        paymentProcessingPanel = new JPanel(new GridBagLayout());
        paymentProcessingPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Processing Payment");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        paymentProcessingPanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        processingBar = new JProgressBar(0, 100);
        processingBar.setStringPainted(true);
        processingBar.setPreferredSize(new Dimension(400, 25));
        paymentProcessingPanel.add(processingBar, gbc);

        gbc.gridy = 2;
        processingStatus = new JLabel("Connecting to payment gateway...");
        processingStatus.setHorizontalAlignment(SwingConstants.CENTER);
        paymentProcessingPanel.add(processingStatus, gbc);

        gbc.gridy = 3;
        confirmPaymentButton = new JButton("Confirm Payment");
        confirmPaymentButton.setEnabled(false);
        confirmPaymentButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmPaymentButton.setBackground(new Color(39, 174, 96));
        confirmPaymentButton.setForeground(Color.GRAY);
        confirmPaymentButton.setPreferredSize(new Dimension(200, 40));
        paymentProcessingPanel.add(confirmPaymentButton, gbc);

        return paymentProcessingPanel;
    }

    private JPanel createReceiptPanel() {
        receiptPanel = new JPanel(new BorderLayout(10, 10));
        receiptPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        receiptArea = new JTextArea();
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(true);
        receiptPanel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        printButton = new JButton("Print Receipt");
        printButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        printButton.setBackground(new Color(41, 128, 185));
        printButton.setForeground(Color.GRAY);
        printButton.setPreferredSize(new Dimension(150, 35));
        buttonPanel.add(printButton);

        emailButton = new JButton("Email Receipt");
        emailButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailButton.setBackground(new Color(52, 152, 219));
        emailButton.setForeground(Color.GRAY);
        emailButton.setPreferredSize(new Dimension(150, 35));
        buttonPanel.add(emailButton);

        doneButton = new JButton("Done");
        doneButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        doneButton.setBackground(new Color(39, 174, 96));
        doneButton.setForeground(Color.GRAY);
        doneButton.setPreferredSize(new Dimension(120, 35));
        buttonPanel.add(doneButton);

        receiptPanel.add(buttonPanel, BorderLayout.SOUTH);

        return receiptPanel;
    }

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
            cardLayout.show(mainPanel, "PAYMENT_SELECTION");
        } else {
            statusLabel.setText("Invalid credentials. Please try again.");
        }
    }

    private void checkFinancialAidOption() {
        String selected = (String) paymentMethodCombo.getSelectedItem();
        if ("Financial Aid".equals(selected)) {
            if (currentPayment.validateFinancialAid()) {
                JOptionPane.showMessageDialog(this,
                    "Financial Aid approved. Amount will be adjusted.",
                    "Financial Aid Status", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Financial Aid not available. Please select another method.",
                    "Financial Aid Status", JOptionPane.WARNING_MESSAGE);
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
            JOptionPane.showMessageDialog(this, "Invalid amount: " + e.getMessage(),
                "Payment Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        amountLabel.setText(String.format("$%.2f", amount));

        processingBar.setValue(0);
        processingStatus.setText("Connecting to payment gateway...");
        confirmPaymentButton.setEnabled(false);
        startProcessing();

        cardLayout.show(mainPanel, "PAYMENT_PROCESSING");
    }

    private void cancelPayment() {
        int result = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel the payment?",
            "Confirm Cancel", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            controller.rollback();
            cardLayout.show(mainPanel, "LOGIN");
            studentIdField.setText("");
            passwordField.setText("");
            statusLabel.setText("");
        }
    }

    private void startProcessing() {
        Timer timer = new Timer(500, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 10;
                processingBar.setValue(progress);

                if (progress == 30) {
                    processingStatus.setText("Authorizing transaction...");
                    controller.authorizeTransaction(currentPayment.getAmount(),
                                                   currentPayment.getPaymentMethod());
                } else if (progress == 60) {
                    processingStatus.setText("Processing with gateway...");
                    currentPayment.processPayment();
                } else if (progress == 80) {
                    processingStatus.setText("Confirming payment...");
                    currentPayment.setTransactionID("TXN" + System.currentTimeMillis());
                } else if (progress >= 100) {
                    ((Timer) e.getSource()).stop();
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
                cardLayout.show(mainPanel, "RECEIPT");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Payment confirmation failed. Please try again.",
                    "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                "Payment Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayReceipt(Receipt receipt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String receiptNumber = receipt.getReceiptID() != null ?
            receipt.getReceiptID() : "RCP" + System.currentTimeMillis();
        receipt.setReceiptID(receiptNumber);

        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("       TUITION PAYMENT RECEIPT\n");
        sb.append("========================================\n\n");
        sb.append("Receipt Number: ").append(receiptNumber).append("\n");
        sb.append("Transaction ID: ").append(receipt.getTransactionID() != null ?
            receipt.getTransactionID() : currentPayment.getTransactionID()).append("\n");
        sb.append("Student ID:     ").append(receipt.getStudentID()).append("\n");
        sb.append("Payment ID:     ").append(currentPayment.getPaymentID() != null ?
            currentPayment.getPaymentID() : "PAY" + System.currentTimeMillis()).append("\n");
        sb.append("\n----------------------------------------\n");
        sb.append("Amount Paid:    $").append(String.format("%.2f", receipt.getAmount())).append("\n");
        sb.append("Payment Method: ").append(receipt.getPaymentMethod()).append("\n");
        sb.append("Date/Time:      ").append(sdf.format(new Date())).append("\n");
        sb.append("\n========================================\n");
        sb.append("     PAYMENT COMPLETED SUCCESSFULLY\n");
        sb.append("========================================\n");

        receiptArea.setText(sb.toString());
    }

    private void printReceipt() {
        try {
            receiptArea.print();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Print error: " + e.getMessage(),
                "Print Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void emailReceipt() {
        String email = JOptionPane.showInputDialog(this,
            "Enter email address to send receipt:",
            "Email Receipt", JOptionPane.QUESTION_MESSAGE);

        if (email != null && !email.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Receipt sent to: " + email,
                "Email Sent", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void closeApplication() {
        int result = JOptionPane.showConfirmDialog(this,
            "Payment completed. Exit the application?",
            "Exit", JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            cardLayout.show(mainPanel, "LOGIN");
            studentIdField.setText("");
            passwordField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new TuitionUI().setVisible(true);
        });
    }
}
