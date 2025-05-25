import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/atm_db"; // Replace with your DB name
    private static final String USER = "root"; // Your DB username
    private static final String PASS = "MySomu#2006"; // Your DB password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
