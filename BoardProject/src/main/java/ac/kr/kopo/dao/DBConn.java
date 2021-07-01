package ac.kr.kopo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection dbConn;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if (dbConn == null) {
			String url = "jdbc:mysql://192.168.23.71:3306/kopo36";
			String user = "root";
			String pass = "wjdthdud12";
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConn = DriverManager.getConnection(url, user, pass);
		}
		return dbConn;
	}
	
	public static void close() throws SQLException {
		if (dbConn != null) {
			if (!dbConn.isClosed()) {
				dbConn.close();
			}
		}
	}
}
