import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WithdrawFrame extends JFrame {
    private int userId;
    private double balance;

    public WithdrawFrame(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;

        setTitle("Withdraw - GU Bank of India");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Enter amount to withdraw (multiples of 100):");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        JTextField amountField = new JTextField(20);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(amountField, gbc);

        JButton withdrawBtn = new JButton("Withdraw");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(withdrawBtn, gbc);

        JButton backBtn = new JButton("Back");
        gbc.gridx = 1;
        panel.add(backBtn, gbc);

        add(panel);

        withdrawBtn.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            try {
                int amount = Integer.parseInt(amountText);
                if (amount <= 0 || amount % 100 != 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be a positive multiple of 100.");
                    return;
                }
                if (amount > balance) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance.");
                    return;
                }
                // TODO: Perform withdrawal logic, update DB etc.

                JOptionPane.showMessageDialog(this, "Withdrawal successful: ₹" + amount);
                dispose();
                new MainMenuFrame("User", balance - amount, userId);  // adjust username accordingly

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid numeric amount.");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new MainMenuFrame("User", balance, userId);
        });

        setVisible(true);
    }
}
