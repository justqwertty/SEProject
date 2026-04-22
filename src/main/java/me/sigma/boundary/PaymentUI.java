package me.sigma.boundary;

import javax.swing.*;
import java.awt.*;

/**
 * Payment UI boundary class - provides dialog-based UI methods
 * for payment operations as shown in the sequence diagram.
 */
public class PaymentUI {
    private JFrame parentFrame;

    public PaymentUI() {
    }

    public PaymentUI(JFrame parent) {
        this.parentFrame = parent;
    }

    /**
     * Shows the main tuition payment GUI
     */
    public void processPayment() {
        SwingUtilities.invokeLater(() -> {
            TuitionPaymentGUI gui = new TuitionPaymentGUI();
            if (parentFrame != null) {
                gui.setLocation(parentFrame.getLocation());
            }
            gui.setVisible(true);
        });
    }

    /**
     * Displays payment section access dialog
     */
    public void accessPaymentSection() {
        JOptionPane.showMessageDialog(parentFrame,
            "Accessing Tuition Payment Section...",
            "Payment Portal",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows alert message to user
     */
    public void alertUser(String message) {
        JOptionPane.showMessageDialog(parentFrame,
            message,
            "Alert",
            JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Shows blocked access dialog
     */
    public void blockAccess(String reason) {
        JOptionPane.showMessageDialog(parentFrame,
            "Access Blocked: " + reason,
            "Access Denied",
            JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Shows payment success confirmation
     */
    public void showPaymentSuccess(String receiptNumber) {
        JOptionPane.showMessageDialog(parentFrame,
            "Payment completed successfully!\nReceipt: " + receiptNumber,
            "Payment Successful",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows payment failure message
     */
    public void showPaymentFailure(String reason) {
        JOptionPane.showMessageDialog(parentFrame,
            "Payment failed: " + reason,
            "Payment Failed",
            JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Confirms payment cancellation
     */
    public boolean confirmCancel() {
        int result = JOptionPane.showConfirmDialog(parentFrame,
            "Are you sure you want to cancel?",
            "Cancel Payment",
            JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }
}
