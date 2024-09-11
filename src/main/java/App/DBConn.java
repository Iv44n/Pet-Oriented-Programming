package App;

import java.sql.*;
import io.github.cdimascio.dotenv.Dotenv;

public class DBConn {

    private static final Dotenv dotenv = Dotenv.load();
    private static final String DB_URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("USER");
    private static final String PASSWORD = dotenv.get("PASSWORD");

    private static Connection conn;

    public Connection getConnectionDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to database");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conn;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
