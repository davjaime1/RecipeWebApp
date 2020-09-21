package com.davjaime1.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.davjaime1.model.User;
import com.davjaime1.sql.SQLConnection;

public abstract class UserDAO
{
	private static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void addNewUser(User user)
	{
		Statement stmt = null;
		Connection conn = SQLConnection.getDBConnection(); 
		String queryString = "INSERT INTO users (username, password, email, role_id) " +
							 "VALUES ('" + user.getUsername() +"','" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getRoleId() +"');";
		try {
            stmt = conn.createStatement();
            stmt.executeUpdate(queryString);
            conn.commit();
		} 
		catch (SQLException e)
		{
			System.out.println("Whoops");
		}
	}
}
