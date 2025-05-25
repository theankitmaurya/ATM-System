import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    private JTextField accountField;
    private JPasswordField pinField;

    public LoginFrame() {
        setTitle("Login - GU Bank of India");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Background image
        ImageIcon bgIcon = new ImageIcon("C:/Users/somuk/IdeaProjects/ATMSystem/src/background.jpg");
        JLabel background = new JLabel(bgIcon);
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // Top Panel with logo and bank name
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        // Logo
        ImageIcon logoIcon = new ImageIcon("C:/Users/somuk/IdeaProjects/ATMSystem/src/logo.png");
        Image logoImg = logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Bank Name Label
        JLabel bankNameLabel = new JLabel(
                "<html><span style='color:red; font-size:40px; font-weight:bold;'>GU</span> " +
                        "<span style='color:black; font-size:40px;'>Bank of India</span></html>",
                SwingConstants.CENTER);
        bankNameLabel.setOpaque(false);
        topPanel.add(bankNameLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center Panel with login form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel accountLabel = new JLabel("Enter Account/Card Number:");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        accountLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(accountLabel, gbc);

        accountField = new JTextField(20);
        gbc.gridy = 1;
        formPanel.add(accountField, gbc);

        JLabel pinLabel = new JLabel("Enter PIN:");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pinLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        formPanel.add(pinLabel, gbc);

        pinField = new JPasswordField(20);
        gbc.gridy = 3;
        formPanel.add(pinField, gbc);

        JButton loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy = 4;
        formPanel.add(loginBtn, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Login button action
        loginBtn.addActionListener(e -> {
            String account = accountField.getText().trim();
            String pin = new String(pinField.getPassword()).trim();

            if (account.isEmpty() || pin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            } else {
                loginUser(account, pin);
            }
        });

        setVisible(true);
    }

    private void loginUser(String accountNo, String pin) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT id, username, balance FROM users WHERE account_no = ? AND pin = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, accountNo);
            pst.setString(2, pin);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                String userName = rs.getString("username");
                double balance = rs.getDouble("balance");

                JOptionPane.showMessageDialog(this, "Login successful. Welcome, " + userName + "!");
                dispose();
                new MainMenuFrame(userName, balance, userId);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid account number or PIN.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection error.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
