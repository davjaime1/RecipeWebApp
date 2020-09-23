package com.davjaime1.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.davjaime1.model.GeneralUser;
import com.davjaime1.model.User;
import com.davjaime1.model.Post;
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
	    int user_id = 0;
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
            user_id = rs.getInt("user_id");
		} 
		catch (SQLException e)
		{
			System.out.println("Could not get user from db");
		}
		user = new GeneralUser(username, password, email, role, user_id);
		return user;
	}
	
	public static void postRecipe(String title, String desc, String inst, InputStream input, int user_id)
	{
        try
        {
        	Connection conn = SQLConnection.getDBConnection();
            // constructs SQL statement
            String query = "INSERT INTO recipe (title, description, instruction, photo, user_id) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, desc);
            statement.setString(3, inst);
            statement.setBlob(4, input);
            statement.setInt(5, user_id);
            int status = statement.executeUpdate();
            if(status > 0) {
                System.out.println("Record is inserted successfully !!!");
             }
             conn.commit();
        }
        catch (SQLException ex)
        {
        	ex.printStackTrace();       
        }
	}
	
	public static List<Post> getPost()
	{	    
	    String query = "SELECT * FROM recipe r";
	    List<Post> postList = new ArrayList<Post>();
	    try(Connection conn = SQLConnection.getDBConnection(); Statement stmt = conn.createStatement();)
	    {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) 
            {
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String instruction = rs.getString("instruction");
                String user_id = rs.getString("user_id");
                Blob blob = rs.getBlob("photo");
                 
                InputStream inputStream = blob.getBinaryStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                 
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);                  
                }
                 
                byte[] imageBytes = outputStream.toByteArray();
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                 
                 
                inputStream.close();
                outputStream.close();
               
                Post p = new Post();
                p.setTitle(title);
                p.setDescription(desc);
                p.setInstructions(instruction);
                p.setUserId(user_id);
                p.setPhoto(base64Image);
                postList.add(p);
            }
        }
	    catch (SQLException | IOException ex)
	    {
            ex.printStackTrace();
        }
	    return postList;
	}
}
