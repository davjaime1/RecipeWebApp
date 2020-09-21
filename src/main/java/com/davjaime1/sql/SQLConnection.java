package com.davjaime1.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private static String DB_DRIVER;
	private static String DB_CONNECTION;
	private static String DB_USER;
	private static String DB_PASSWORD; 
	private static SQLConnection single_instance = null;
	private SQLConnection() {
		DB_DRIVER = "com.mysql.cj.jdbc.Driver";
		DB_CONNECTION  = "jdbc:mysql://mysql-davjaime1-testdb.ctno8n9igcgw.us-east-2.rds.amazonaws.com:3307/recipe?autoReconnect=true&useSSL=false";
		DB_USER  = "masterUsername";
		DB_PASSWORD = "password";
	}
	public static synchronized SQLConnection getInstance() {
        if (single_instance == null)
        	single_instance = new SQLConnection();
        return single_instance;
	}

	public static Connection getDBConnection() {	
		Connection dbConnection = null;	 

		try {	 
			Class.forName(DB_DRIVER);	 
		} catch (ClassNotFoundException e) {	 
		}

		try {	 
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			dbConnection.setAutoCommit(false);
		} catch (SQLException e) {	    
		}	 
		return dbConnection;	 
	}
}
