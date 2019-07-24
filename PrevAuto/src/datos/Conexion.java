package datos;


import java.sql.*;


public class Conexion {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pa?useSSL=false";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "steven14";
	
	public static synchronized Connection getConnection()throws SQLException{
		try {
			Class.forName(JDBC_DRIVER);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(JDBC_URL,JDBC_USER, JDBC_PASS);
	}
	
	public static void close(Connection c) {
		try {
			if(c != null) {
				c.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
