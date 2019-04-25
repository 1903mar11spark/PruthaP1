package com.revature.projectone.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
 
//	static String plug = "Connection.properties";
	
	public static Connection getConnectionFromFile() throws SQLException, IOException {
		Properties properties = new Properties();
		InputStream file = ConnectionUtil.class.getClassLoader().getResourceAsStream("Connection.properties");
		properties.load(file);
		try {
			   Class.forName("oracle.jdbc.OracleDriver");
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			   System.out.println("Error: unable to load driver class!");
			}
		
		return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"),
				properties.getProperty("password"));
}
	
	
//	public static Connection getConnection() throws SQLException {
//		String url = "";
//		String username = "";
//		String password = "";
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			System.out.println("Error: Could not load the driver class");
//		}
//		return DriverManager.getConnection(url, username, password);
//	}
//
//	
//	public static void main(String[] args) {
//		String plug = "/Users/pruthapatel/RevatureSpace/projectone/src/main/Connection.properties";
//		  try 
//		  { 
//		  Connection con = ConnectionUtil.getConnectionFromFile(plug);
//		  System.out.println(con); 
//		  }
//		  catch (SQLException e) 
//		  { 
//			  e.printStackTrace(); 
//		  }
//		  catch (IOException e) 
//		  {
//			  e.printStackTrace(); 
//		  }
//	}
//	public static Connection getConnectionFromFile(String filename) throws SQLException, IOException {
//			Properties properties = new Properties();
//			InputStream in = new FileInputStream(filename);
//			properties.load(in);
//			return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
//		}

	
}

