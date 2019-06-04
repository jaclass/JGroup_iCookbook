package controller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Methods for the database.
 * 
 * @author JGroup
 *
 */
public class DBUtils {
	
	public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/cookbook?serverTimezone=UTC";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "123456";
	
	// Loading the database driver.
	static {
         try {
             Class.forName(DRIVER_CLASS_NAME);
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
	}
	
	/**
	 * Get the connection.
	 * 
	 * @return Database connection.
	 */
	public static Connection getMySqlConn() {
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Close three resources.
	 * 
	 * @param rs ResultSet.
	 * @param stmt Statement.
	 * @param conn Connection.
	 */
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		// Close respectively.
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close two resources.
	 * Overload.
	 * 
	 * @param stmt Statement.
	 * @param conn Connection.
	 */
	public static void close(Statement stmt, Connection conn) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Close one resouce.
	 * Overload.
	 * 
	 * @param conn Connection.
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Main function to test.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println(DBUtils.getMySqlConn());
	}
	
}
