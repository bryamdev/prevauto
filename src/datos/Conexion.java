package datos;


import java.sql.*;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;




public class Conexion {
	
	/*
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pa?useSSL=false";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "admin";
	*/
	
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://remotemysql.com:3306/hh4pFTYsk4?useSSL=false";
	private static final String JDBC_USER = "hh4pFTYsk4";
	private static final String JDBC_PASS = "3DggfloneO";
	
	
	
	public static DataSource getDataSource() {
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(JDBC_DRIVER);
		basicDataSource.setUrl(JDBC_URL);
		basicDataSource.setUsername(JDBC_USER);
		basicDataSource.setPassword(JDBC_PASS);
		basicDataSource.setInitialSize(1);
		basicDataSource.setMaxTotal(8);
		
		DataSource dataSource = basicDataSource;
		
		return dataSource;	
	}
	
	public static synchronized Connection getConnection()throws SQLException{
		
		/*
		try {
			Class.forName(JDBC_DRIVER);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(JDBC_URL,JDBC_USER, JDBC_PASS);
		*/
		
		return getDataSource().getConnection();
		
	}
	
	
	public static void close(Connection con) {
		try {
			if(con != null) {
				con.close();
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
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt != null) {
				stmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
