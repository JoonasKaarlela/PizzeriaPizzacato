package Pizzacato.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataAccessObject {


	protected static Connection getConnection() {
		Connection connection = null;

		String username = "a1500924";
		String password = "xoMAsf72e";
		String url = "jdbc:mariadb://localhost:3306/a1500924";

		
		try {
			Class.forName("org.mariadb.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return connection;
	}

	protected static void close(Statement stmt, Connection connection) {
		close (null, stmt, connection);
	}


	protected static void close(ResultSet rs, Statement stmt, Connection conn ) {
		
		try {
			if (rs != null) { 
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) { 
				conn.close();
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
