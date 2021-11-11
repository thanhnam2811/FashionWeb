package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLServerConnUtils {

	// guess connection
	public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {
		// Thông tin database
		String hostName = "localhost";
		String database = "QuanLiShop";
		String userName = "guess";
		String password = "guess";

		return getSQLServerConnection(hostName, database,
				userName, password);
	}

	// user connection
	public static Connection getSQLServerConnection(String userName, String password) throws ClassNotFoundException, SQLException {
		// Thông tin database
		String hostName = "localhost";
		String database = "QuanLiShop";

		return getSQLServerConnection(hostName, database,
				userName, password);
	}

	public static Connection getSQLServerConnection(String hostName, String database, String userName, String password)
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
		return conn;
	}
}