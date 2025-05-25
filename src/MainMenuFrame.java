import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuFrame extends JFrame {
    private String userName;
    private double balance;
    private int userId;

    public MainMenuFrame(String userName, double balance, int userId) {
        this.userName = userName;
        this.balance = balance;
        this.userId = userId;

        setTitle("GU Bank of India - Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Set background image
        ImageIcon bgIcon = new ImageIcon("C:/Users/somuk/IdeaProjects/ATMSystem/src/background.jpg");
        JLabel backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setLayout(new BorderLayout());
        setContentPane(backgroundLabel);

        // Top panel: logo + bank name
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Load and scale logo
        ImageIcon logoIconOriginal = new ImageIcon("C:/Users/somuk/IdeaProjects/ATMSystem/src/logo.png");
        Image logoImg = logoIconOriginal.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Bank name with color styling using HTML
        JLabel bankNameLabel = new JLabel(
                "<html><span style='color:red; font-size:48px; font-weight:bold;'>GU</span> " +
                        "<span style='color:black; font-size:48px;'>Bank of India</span></html>",
                SwingConstants.CENTER);
        bankNameLabel.setOpaque(false);
        topPanel.add(bankNameLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // Center panel with welcome, balance and buttons
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, " + userName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(welcomeLabel, gbc);

        // Balance label
        JLabel balanceLabel = new JLabel("Current Balance: ₹" + String.format("%.2f", balance));
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        balanceLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        centerPanel.add(balanceLabel, gbc);

        // Buttons
        JButton btnWithdraw = new JButton("Withdraw");
        JButton btnDeposit = new JButton("Deposit");
        JButton btnTransactionHistory = new JButton("Transaction History");
        JButton btnCardlessCash = new JButton("Cardless Cash");
        JButton btnLogout = new JButton("Logout");

        Dimension btnSize = new Dimension(220, 50);
        btnWithdraw.setPreferredSize(btnSize);
        btnDeposit.setPreferredSize(btnSize);
        btnTransactionHistory.setPreferredSize(btnSize);
        btnCardlessCash.setPreferredSize(btnSize);
        btnLogout.setPreferredSize(btnSize);

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridy = 2;
        gbc.gridx = 0;
        centerPanel.add(btnWithdraw, gbc);

        gbc.gridx = 1;
        centerPanel.add(btnDeposit, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        centerPanel.add(btnTransactionHistory, gbc);

        gbc.gridx = 1;
        centerPanel.add(btnCardlessCash, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        centerPanel.add(btnLogout, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Button Actions
        btnWithdraw.addActionListener(e -> {
            dispose();
            new WithdrawFrame(userId, balance);
        });

        btnDeposit.addActionListener(e -> {
            dispose();
            new DepositFrame(userId, balance);
        });

        btnTransactionHistory.addActionListener(e -> {
            dispose();
            new TransactionHistoryFrame(userId);
        });

        btnCardlessCash.addActionListener(e -> {
            dispose();
            new CardlessCashFrame();
        });

        btnLogout.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }

    // Test main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainMenuFrame("Somuk", 15000.0, 1));
    }
}
