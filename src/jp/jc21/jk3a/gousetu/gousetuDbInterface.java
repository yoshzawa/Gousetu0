package jp.jc21.jk3a.gousetu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface gousetuDbInterface {
	final String driverName = "oracle.jdbc.driver.OracleDriver";
	final String url = "jdbc:oracle:thin:@192.168.54.223:1521/orcl";
	final String id = "gousetu0";
	final String pass = "gousetu0";
	
	static Connection getStatement() {
		try {
			Class.forName(driverName);
			Connection connection = DriverManager.getConnection(url, id, pass);
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
