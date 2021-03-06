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
	
	public static void postRecipe(String title, String desc, String inst, InputStream input, int user_id, int viewPostId)
	{
        try
        {
        	Connection conn = SQLConnection.getDBConnection();
            // constructs SQL statement
            String query = "INSERT INTO recipe (title, description, instruction, photo, user_id, view_id, views) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, desc);
            statement.setString(3, inst);
            statement.setBlob(4, input);
            statement.setInt(5, user_id);
            statement.setInt(6, viewPostId);
            statement.setInt(7, 0);
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
	
	public static List<Post> queryPost(String query)
	{	    
	    List<Post> postList = new ArrayList<Post>();
	    try(Connection conn = SQLConnection.getDBConnection(); Statement stmt = conn.createStatement();)
	    {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) 
            {
            	int postId = Integer.parseInt(rs.getString("recipe_id"));
                String title = rs.getString("title");
                String desc = rs.getString("description");
                String instruction = rs.getString("instruction");
                String user_id = rs.getString("user_id");
                int view_id = rs.getInt("view_id");
                int views = rs.getInt("views");
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
                p.setPostId(postId);
                p.setTitle(title);
                p.setDescription(desc);
                p.setInstructions(instruction);
                p.setUserId(user_id);
                p.setPhoto(base64Image);
                p.setViewId(view_id);
                p.setViews(views);
                postList.add(p);
            }
        }
	    catch (SQLException | IOException ex)
	    {
            ex.printStackTrace();
        }
	    return postList;
	}
	
	public static List<Post> getAllPost()
	{
		String query = "SELECT * FROM recipe r WHERE r.view_id = '" + 1 + "' ORDER BY r.views DESC LIMIT 10";
		List<Post> postList = new ArrayList<Post>();
		postList = queryPost(query);
		return postList;
	}
	
	public static List<Post> getAdminAllPost()
	{
		String query = "SELECT * FROM recipe r";
		List<Post> postList = new ArrayList<Post>();
		postList = queryPost(query);
		return postList;
	}
	
	public static List<Post> getAllMyPosts(int user_id)
	{
		String query = "SELECT * FROM recipe r WHERE r.user_id = '" + user_id + "'";
		List<Post> postList = new ArrayList<Post>();
		postList = queryPost(query);
		return postList;
	}
	
	public static Post getSpecificPost(int postId)
	{
		String query = "SELECT * FROM recipe r WHERE r.recipe_id = '" + postId + "'";
		List<Post> postList = new ArrayList<Post>();
		postList = queryPost(query);
		return postList.get(0);
	}
	
	public static String getPostUser(String userId)
	{
		Statement stmt = null;
	    Connection conn = SQLConnection.getDBConnection();
	    String query = "SELECT username FROM users u WHERE u.user_id = '" + userId +"'";
	    String username ="";
		try
		{
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            username = rs.getString("username");
		} 
		catch (SQLException e)
		{
			System.out.println("Could not get user from db");
		}
		return username;
	}
	
	public static void upView(int postId)
	{
		String query = "UPDATE recipe SET views = views + 1 WHERE recipe_id = '" + postId + "'";
		executeQuery(query);
	}
	
	public static void executeQuery(String query)
	{
		Statement stmt = null;
	    Connection conn = SQLConnection.getDBConnection();
		try
		{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.commit();
		} 
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	public static void deleteSpecificPost(int postNum)
	{
		String query = "DELETE FROM recipe r WHERE r.recipe_id = '" + postNum + "'";
		executeQuery(query);
	}
	
	public static void changeVisPost(int postNum, int vis)
	{
		if(vis==1)
		{
			vis=0;
		}
		else
		{
			vis=1;
		}
		String query = "UPDATE recipe r SET view_id = '" + vis + "' WHERE r.recipe_id = '" + postNum + "'";
		executeQuery(query);
	}
}
