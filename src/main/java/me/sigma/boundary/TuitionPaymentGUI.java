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

/**
 * Main GUI for Tuition Payment System based on UC-02 Sequence Diagram
 * Implements the payment flow: Student -> PaymentUI -> PaymentController -> PaymentGateway
 */
public class TuitionPaymentGUI extends JFrame {
    private PaymentController controller;
    private Payment currentPayment;

    // Cards for different payment stages
    private CardLayout cardLayout;
    private JPanel mainPanel;

    // UI Components
    private JTextField studentIdField;
    private JPasswordField passwordField;
    private JComboBox<String> paymentMethodCombo;
    private JLabel amountLabel;
    private JLabel balanceLabel;
    private JTextArea receiptArea;
    private JProgressBar processingBar;
    private JLabel statusLabel;

    // Payment method options
    private static final String[] PAYMENT_METHODS = {
        "Credit Card", "Debit Card", "Bank Transfer", "PayPal", "Financial Aid"
    };

    public TuitionPaymentGUI() {
        controller = new PaymentController();
        currentPayment = new Payment();

        initUI();
    }

    private void initUI() {
        setTitle("Tuition Payment System - UC-02");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createPaymentSelectionPanel(), "PAYMENT_SELECTION");
        mainPanel.add(createPaymentProcessingPanel(), "PAYMENT_PROCESSING");
        mainPanel.add(createReceiptPanel(), "RECEIPT");

        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Tuition Payment System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Student Login");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        panel.add(subtitleLabel, gbc);

        // Student ID
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        studentIdField = new JTextField(15);
        panel.add(studentIdField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        panel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setBackground(new Color(41, 128, 185));
        loginButton.setForeground(Color.WHITE);
        loginButton.setPreferredSize(new Dimension(200, 40));
        loginButton.addActionListener(e -> handleLogin());
        panel.add(loginButton, gbc);

        // Status label
        gbc.gridy = 5;
        statusLabel = new JLabel("");
        statusLabel.setForeground(Color.RED);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(statusLabel, gbc);

        return panel;
    }

    private JPanel createPaymentSelectionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header
        JLabel headerLabel = new JLabel("Tuition Payment Details");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(headerLabel, gbc);

        // Student info section
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        infoPanel.add(new JLabel("Student ID:"));
        JLabel studentIdDisplay = new JLabel("");
        studentIdDisplay.setName("studentIdDisplay");
        infoPanel.add(studentIdDisplay);

        infoPanel.add(new JLabel("Outstanding Balance:"));
        balanceLabel = new JLabel("$0.00");
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(192, 57, 43));
        infoPanel.add(balanceLabel);

        infoPanel.add(new JLabel("Financial Aid Status:"));
        JLabel aidStatusLabel = new JLabel("Not Applied");
        aidStatusLabel.setName("aidStatusLabel");
        infoPanel.add(aidStatusLabel);

        panel.add(infoPanel, gbc);

        // Payment amount section
        gbc.gridy = 2;
        JPanel amountPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        amountPanel.setBorder(BorderFactory.createTitledBorder("Payment Amount"));

        amountPanel.add(new JLabel("Amount Due:"));
        amountLabel = new JLabel("$0.00");
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        amountPanel.add(amountLabel);

        panel.add(amountPanel, gbc);

        // Payment method selection
        gbc.gridy = 3;
        panel.add(new JLabel("Select Payment Method:"), gbc);

        gbc.gridy = 4;
        paymentMethodCombo = new JComboBox<>(PAYMENT_METHODS);
        paymentMethodCombo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        paymentMethodCombo.setPreferredSize(new Dimension(250, 35));
        paymentMethodCombo.addActionListener(e -> checkFinancialAidOption());
        panel.add(paymentMethodCombo, gbc);

        // Buttons
        gbc.gridy = 5;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));

        JButton confirmButton = new JButton("Proceed to Payment");
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmButton.setBackground(new Color(39, 174, 96));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setPreferredSize(new Dimension(180, 40));
        confirmButton.addActionListener(e -> proceedToPayment());
        buttonPanel.add(confirmButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cancelButton.setBackground(new Color(149, 165, 166));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setPreferredSize(new Dimension(120, 40));
        cancelButton.addActionListener(e -> cancelPayment());
        buttonPanel.add(cancelButton);

        panel.add(buttonPanel, gbc);

        return panel;
    }

    private JPanel createPaymentProcessingPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(40, 40, 40, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel titleLabel = new JLabel("Processing Payment");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Processing animation
        gbc.gridy = 1;
        processingBar = new JProgressBar(0, 100);
        processingBar.setStringPainted(true);
        processingBar.setPreferredSize(new Dimension(400, 25));
        panel.add(processingBar, gbc);

        // Status
        gbc.gridy = 2;
        JLabel processingStatus = new JLabel("Connecting to payment gateway...");
        processingStatus.setName("processingStatus");
        processingStatus.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(processingStatus, gbc);

        // Payment details
        gbc.gridy = 3;
        JPanel detailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Transaction Details"));

        detailsPanel.add(new JLabel("Payment Method:"));
        JLabel methodLabel = new JLabel("");
        methodLabel.setName("methodLabel");
        detailsPanel.add(methodLabel);

        detailsPanel.add(new JLabel("Amount:"));
        JLabel processAmountLabel = new JLabel("");
        processAmountLabel.setName("processAmountLabel");
        detailsPanel.add(processAmountLabel);

        detailsPanel.add(new JLabel("Transaction ID:"));
        JLabel transIdLabel = new JLabel("Generating...");
        transIdLabel.setName("transIdLabel");
        detailsPanel.add(transIdLabel);

        detailsPanel.add(new JLabel("Status:"));
        JLabel processStatusLabel = new JLabel("PENDING");
        processStatusLabel.setName("processStatusLabel");
        detailsPanel.add(processStatusLabel);

        panel.add(detailsPanel, gbc);

        // Confirm button (initially hidden)
        gbc.gridy = 4;
        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.setName("confirmButton");
        confirmButton.setEnabled(false);
        confirmButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        confirmButton.setBackground(new Color(39, 174, 96));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setPreferredSize(new Dimension(200, 40));
        confirmButton.addActionListener(e -> confirmPayment());
        panel.add(confirmButton, gbc);

        // Start processing
        startProcessing(processingBar, processingStatus, methodLabel,
                       processAmountLabel, transIdLabel, processStatusLabel, confirmButton);

        return panel;
    }

    private JPanel createReceiptPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Receipt area
        receiptArea = new JTextArea();
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        receiptArea.setEditable(false);
        receiptArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton printButton = new JButton("Print Receipt");
        printButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        printButton.setBackground(new Color(41, 128, 185));
        printButton.setForeground(Color.WHITE);
        printButton.setPreferredSize(new Dimension(150, 35));
        printButton.addActionListener(e -> printReceipt());
        buttonPanel.add(printButton);

        JButton emailButton = new JButton("Email Receipt");
        emailButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailButton.setBackground(new Color(52, 152, 219));
        emailButton.setForeground(Color.WHITE);
        emailButton.setPreferredSize(new Dimension(150, 35));
        emailButton.addActionListener(e -> emailReceipt());
        buttonPanel.add(emailButton);

        JButton doneButton = new JButton("Done");
        doneButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        doneButton.setBackground(new Color(39, 174, 96));
        doneButton.setForeground(Color.WHITE);
        doneButton.setPreferredSize(new Dimension(120, 35));
        doneButton.addActionListener(e -> closeApplication());
        buttonPanel.add(doneButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    // Event handlers
    private void handleLogin() {
        String studentId = studentIdField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (studentId.isEmpty() || password.isEmpty()) {
            statusLabel.setText("Please enter both Student ID and Password");
            return;
        }

        // Validate credentials through controller
        if (controller.validateCredentials(studentId)) {
            // Compute fees for student
            controller.computeFees(studentId);

            // Set student info in payment selection
            JPanel selectionPanel = getPanelByName("PAYMENT_SELECTION");
            if (selectionPanel != null) {
                JLabel studentIdDisplay = findLabelByName(selectionPanel, "studentIdDisplay");
                if (studentIdDisplay != null) {
                    studentIdDisplay.setText(studentId);
                }
            }

            // Update balance (simulated - in real app, get from controller)
            balanceLabel.setText("$5,250.00");

            currentPayment.setStudentID(studentId);
            cardLayout.show(mainPanel, "PAYMENT_SELECTION");
        } else {
            statusLabel.setText("Invalid credentials. Please try again.");
        }
    }

    private void checkFinancialAidOption() {
        String selectedMethod = (String) paymentMethodCombo.getSelectedItem();
        if ("Financial Aid".equals(selectedMethod)) {
            if (currentPayment.validateFinancialAid()) {
                JOptionPane.showMessageDialog(this,
                    "Financial Aid approved. Amount will be adjusted.",
                    "Financial Aid Status",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Financial Aid not available. Please select another method.",
                    "Financial Aid Status",
                    JOptionPane.WARNING_MESSAGE);
                paymentMethodCombo.setSelectedIndex(0);
            }
        }
    }

    private void proceedToPayment() {
        String method = (String) paymentMethodCombo.getSelectedItem();
        currentPayment.setPaymentMethod(method);

        // Set amount based on payment method
        double amount = 5250.00; // Would come from controller in real app
        if ("Financial Aid".equals(method)) {
            amount = 3500.00; // Adjusted amount
        }
        try {
            currentPayment.setAmount(amount);
        } catch (PaymentException e) {
            JOptionPane.showMessageDialog(this,
                "Invalid amount: " + e.getMessage(),
                "Payment Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        amountLabel.setText(String.format("$%.2f", amount));

        cardLayout.show(mainPanel, "PAYMENT_PROCESSING");
    }

    private void cancelPayment() {
        int result = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel the payment?",
            "Confirm Cancel",
            JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            controller.rollback();
            cardLayout.show(mainPanel, "LOGIN");
            studentIdField.setText("");
            passwordField.setText("");
            statusLabel.setText("");
        }
    }

    private void startProcessing(JProgressBar bar, JLabel status, JLabel methodLabel,
                                 JLabel amountLabel, JLabel transIdLabel,
                                 JLabel processStatusLabel, JButton confirmButton) {
        Timer timer = new Timer(500, new ActionListener() {
            int progress = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                progress += 10;
                bar.setValue(progress);

                if (progress == 30) {
                    status.setText("Authorizing transaction...");
                    controller.authorizeTransaction(currentPayment.getAmount(),
                                                   currentPayment.getPaymentMethod());
                } else if (progress == 60) {
                    status.setText("Processing with gateway...");
                    currentPayment.processPayment();
                } else if (progress == 80) {
                    status.setText("Confirming payment...");
                    currentPayment.setTransactionID("TXN" + System.currentTimeMillis());
                } else if (progress >= 100) {
                    ((Timer) e.getSource()).stop();
                    status.setText("Payment authorized. Please confirm.");
                    processStatusLabel.setText("AUTHORIZED");
                    processStatusLabel.setForeground(new Color(39, 174, 96));
                    confirmButton.setEnabled(true);

                    // Update labels
                    methodLabel.setText(currentPayment.getPaymentMethod());
                    amountLabel.setText(String.format("$%.2f", currentPayment.getAmount()));
                    transIdLabel.setText(currentPayment.getTransactionID());
                }
            }
        });
        timer.start();
    }

    private void confirmPayment() {
        try {
            if (currentPayment.confirmPayment()) {
                controller.updateFinancialRecord();

                // Generate and display receipt
                Receipt receipt = controller.generateReceipt(currentPayment);
                displayReceipt(receipt);

                cardLayout.show(mainPanel, "RECEIPT");
            } else {
                JOptionPane.showMessageDialog(this,
                    "Payment confirmation failed. Please try again.",
                    "Payment Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Error: " + e.getMessage(),
                "Payment Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayReceipt(Receipt receipt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String receiptNumber = receipt.getReceiptID() != null ? receipt.getReceiptID() :
                              "RCP" + System.currentTimeMillis();
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
            JOptionPane.showMessageDialog(this,
                "Print error: " + e.getMessage(),
                "Print Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void emailReceipt() {
        String email = JOptionPane.showInputDialog(this,
            "Enter email address to send receipt:",
            "Email Receipt",
            JOptionPane.QUESTION_MESSAGE);

        if (email != null && !email.trim().isEmpty()) {
            // In real implementation, would call receipt.sendReceiptByEmail(email)
            JOptionPane.showMessageDialog(this,
                "Receipt sent to: " + email,
                "Email Sent",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void closeApplication() {
        int result = JOptionPane.showConfirmDialog(this,
            "Payment completed. Exit the application?",
            "Exit",
            JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            cardLayout.show(mainPanel, "LOGIN");
            studentIdField.setText("");
            passwordField.setText("");
        }
    }

    private JPanel getPanelByName(String cardName) {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JPanel) {
                // CardLayout panels don't have names, we identify by content
                return (JPanel) comp;
            }
        }
        return null;
    }

    private JLabel findLabelByName(JPanel panel, String name) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel && name.equals(comp.getName())) {
                return (JLabel) comp;
            }
            if (comp instanceof JPanel) {
                JLabel found = findLabelByName((JPanel) comp, name);
                if (found != null) return found;
            }
        }
        return null;
    }

    // Main method to launch the GUI
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            TuitionPaymentGUI gui = new TuitionPaymentGUI();
            gui.setVisible(true);
        });
    }
}
