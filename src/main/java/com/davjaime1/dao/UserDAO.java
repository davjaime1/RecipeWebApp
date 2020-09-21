package com.davjaime1.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
