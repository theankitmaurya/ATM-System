import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CardlessCashFrame extends JFrame {
    public CardlessCashFrame() {
        setTitle("Cardless Cash");
        setSize(350, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel("Scan the QR to withdraw amount in multiples of 100", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel, BorderLayout.NORTH);

        // Load image using absolute path
        String imagePath = "C:/Users/somuk/IdeaProjects/ATMSystem/src/qr.png";
        ImageIcon qrIcon = new ImageIcon(imagePath);

        JLabel imageLabel = new JLabel(qrIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
