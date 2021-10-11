package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLServerConnUtils {

	public static Connection getSQLServerConnection() throws ClassNotFoundException, SQLException {
		// Thông tin database
		String hostName = "localhost";
	    String database = "QuanLiShop";
	    String userName = "sa";
	    String password = "123";

	    return getSQLServerConnection(hostName, database,
	            userName, password);
	}

	public static Connection getSQLServerConnection(String hostName, String database, String userName, String password)
			throws SQLException, ClassNotFoundException {

		DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

	    // Cấu trúc URL Connection dành cho SQLServer
	    // Ví dụ:
		//jdbc:sqlserver://[serverName[\instanceName][:portNumber]][;property=value[;property=value]]
		//jdbc:sqlserver://localhost:1433;databaseName=testdb;integratedSecurity=true;
	    String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" +
	            ";databaseName=" + database + 
	            ";user=" + userName +
	            ";password=" + password;

	    Connection conn = DriverManager.getConnection(connectionURL, userName,
	            password);
		return conn;
	}
}