package conn;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionUtils {
    // guess connection
    public static Connection getConnection()
            throws ClassNotFoundException, SQLException {
        // get connection
        return SQLServerConnUtils.getSQLServerConnection();
    }

    // user connection
    public static Connection getConnection(String userName, String password)
            throws ClassNotFoundException, SQLException {
        // get connection
        return SQLServerConnUtils.getSQLServerConnection(userName, password);
    }

    public static void closeQuietly(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
        }
    }

    public static void rollbackQuietly(Connection conn) {
        try {
            conn.rollback();
        } catch (Exception e) {
        }
    }
    // Test connection
    public static void main(String[] args) {
        try {
            new ConnectionUtils();
            System.out.print(ConnectionUtils.getConnection());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}