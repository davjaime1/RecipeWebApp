package com.davjaime1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.davjaime1.model.GeneralUser;
import com.davjaime1.model.User;
import com.davjaime1.sql.SQLConnection;

public abstract class UserDAO
{	
	private static SQLConnection DBMgr = SQLConnection.getInstance();
	
	public static void addNewUser(User user)
	{
		try {
			Connection conn = SQLConnection.getDBConnection(); 
			String query = "INSERT INTO users (username, password, email, role_id)" +
								 "VALUES (?,?,?,?)";
			//"VALUES ('" + user.getUsername() +"','" + user.getPassword() + "', '" + user.getEmail() + "', '" + user.getRoleId() +"');";
		
			PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setInt(4, user.getRoleId());
            
            int status = stmt.executeUpdate();
            if(status > 0) {
               System.out.println("Record is inserted successfully !!!");
            }
            conn.commit();
            
		} 
		catch (SQLException e)
		{
			System.out.println("Whoops");
		}
	}
	
	public static boolean loginUser(String username, String password)
	{
		Statement stmt = null;
	    Connection conn = SQLConnection.getDBConnection();
	    String query = "SELECT * FROM users u WHERE u.username = '" + username +"' AND u.password = '" + password + "'";
	    boolean result = false;
		try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next() == false)
            {
            	result = false;
            }
            else
            {
            	result = true;
            }
            
		} 
		catch (SQLException e)
		{
			System.out.println("Could not add a new user to db");
		}
		return result;
	}
	
	public static User getUser(String username, String password)
	{
		Statement stmt = null;
	    Connection conn = SQLConnection.getDBConnection();
	    String query = "SELECT * FROM users u WHERE u.username = '" + username +"' AND u.password = '" + password + "'";
	    User user;
	    String email ="";
	    int role = 0;
		try
		{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            username = rs.getString("username");
            password = rs.getString("password");
            email = rs.getString("email");
            role = rs.getInt("role_id");
		} 
		catch (SQLException e)
		{
			System.out.println("Could not get user from db");
		}
		user = new GeneralUser(username, password, email, role);
		return user;
	}
}
