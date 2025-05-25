import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DepositFrame extends JFrame {
    private int userId;
    private double balance;

    public DepositFrame(int userId, double balance) {
        this.userId = userId;
        this.balance = balance;

        setTitle("Deposit - GU Bank of India");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Enter amount to deposit:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(label, gbc);

        JTextField amountField = new JTextField(20);
        gbc.gridy = 1;
        panel.add(amountField, gbc);

        JButton depositBtn = new JButton("Deposit");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        panel.add(depositBtn, gbc);

        JButton backBtn = new JButton("Back");
        gbc.gridx = 1;
        panel.add(backBtn, gbc);

        add(panel);

        depositBtn.addActionListener(e -> {
            String amountText = amountField.getText().trim();
            try {
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be positive.");
                    return;
                }

                // Update DB balance and insert transaction
                try (Connection con = DBConnection.getConnection()) {
                    con.setAutoCommit(false);

                    String updateBalanceQuery = "UPDATE users SET balance = balance + ? WHERE id = ?";
                    PreparedStatement pst1 = con.prepareStatement(updateBalanceQuery);
                    pst1.setDouble(1, amount);
                    pst1.setInt(2, userId);
                    pst1.executeUpdate();

                    String insertTransactionQuery = "INSERT INTO transactions (user_id, type, amount) VALUES (?, 'deposit', ?)";
                    PreparedStatement pst2 = con.prepareStatement(insertTransactionQuery);
                    pst2.setInt(1, userId);
                    pst2.setDouble(2, amount);
                    pst2.executeUpdate();

                    con.commit();

                    JOptionPane.showMessageDialog(this, "Deposit successful: ₹" + amount);
                    dispose();
                    new MainMenuFrame("User", balance + amount, userId);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Database error during deposit.");
                }

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
