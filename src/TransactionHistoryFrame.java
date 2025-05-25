import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class TransactionHistoryFrame extends JFrame {
    private int userId;

    public TransactionHistoryFrame(int userId) {
        this.userId = userId;
        setTitle("Transaction History");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel(new String[]{"Date & Time", "Type", "Amount"}, 0);
        JTable table = new JTable(model);
        add(new JScrollPane(table));

        loadTransactions(model);

        setVisible(true);
    }

    private void loadTransactions(DefaultTableModel model) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT date, type, amount FROM transactions WHERE user_id = ? ORDER BY date DESC";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");
                model.addRow(new Object[]{date, type, "₹" + amount});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load transaction history.");
        }
    }
}
