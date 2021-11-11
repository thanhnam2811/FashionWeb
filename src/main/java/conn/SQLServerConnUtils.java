package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLServerConnUtils {
    // Thông tin database
    static String hostName = "localhost";
    static String database = "QuanLiShop";

    // guess connection
    public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {
        String userName = "guess";
        String password = "guess";

        return getSQLServerConnection(userName, password);
    }

    public static Connection getSQLServerConnection(String userName, String password)
            throws SQLException, ClassNotFoundException {

        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        // Cấu trúc URL Connection dành cho SQLServer
        // Ví dụ:
        // jdbc:sqlserver://localhost:1433;databaseName=...;user=...;password=...;
        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" +
                ";databaseName=" + database +
                ";user=" + userName +
                ";password=" + password;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        System.out.println("Login as " + userName);
        return conn;
    }
}