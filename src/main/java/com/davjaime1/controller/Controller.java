package com.davjaime1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.davjaime1.dao.UserDAO;
import com.davjaime1.model.Admin;
import com.davjaime1.model.ErrorMsgs;
import com.davjaime1.model.GeneralUser;
import com.davjaime1.model.Post;
import com.davjaime1.model.User;
import com.davjaime1.sql.SQLConnection;

/**
 * Servlet implementation class Controller
 */
@MultipartConfig(maxFileSize = 16177215) 
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		String url = "";
		if(action.equalsIgnoreCase("CreateNewPostPage"))
        {
			url="/CreatePost.jsp";
        }
		else if(action.equalsIgnoreCase("ViewSpecificPost"))
		{
			int postNum = Integer.parseInt(request.getParameter("postNum"));
			//Using the postNum we can now query the specifc post and the set the attributes to display it.
			Post p = UserDAO.getSpecificPost(postNum);
			request.setAttribute("Post", p);	
			String pUser = UserDAO.getPostUser(p.getUserId());
			request.setAttribute("pUser", pUser);
			User u = (User) session.getAttribute("USER");
			//Now we need to update the view count
			UserDAO.upView(p.getPostId());
			url = "/ViewSpecificPost.jsp";
		}
		else if(action.equalsIgnoreCase("logout"))
		{
			session.invalidate();
			url = "/index.jsp";
		}
		else if(action.equalsIgnoreCase("Register"))
		{
			url = "/RegisterForm.jsp";
		}
		else if(action.equalsIgnoreCase("myPosts"))
		{
			//We need to get the current user
			User u = (User) session.getAttribute("USER");
			int user_id = u.getUserId();
			
			//Now we use the user_id to query
			List<Post> postList = new ArrayList<Post>();
			postList = UserDAO.getAllMyPosts(user_id);
			request.setAttribute("Post", postList);
			
			url = "/MyPosts.jsp";
		}
		else if(action.equalsIgnoreCase("delete"))
		{
			int postNum = Integer.parseInt(request.getParameter("postNum"));
			UserDAO.deleteSpecificPost(postNum);
			
			User user = (User) session.getAttribute("USER");
			int user_id = user.getUserId();
			List<Post> postList = new ArrayList<Post>();
			if(user.getRoleId() == 0)
			{
				postList = UserDAO.getAdminAllPost();
			}
			else
			{
				postList = UserDAO.getAllMyPosts(user_id);
			}
			postList = UserDAO.getAdminAllPost();
			request.setAttribute("Post", postList);
			
			url = "/MyPosts.jsp";
		}
		else if(action.equalsIgnoreCase("changeVis"))
		{
			int postNum = Integer.parseInt(request.getParameter("postNum"));
			int vis = Integer.parseInt(request.getParameter("vis"));
			UserDAO.changeVisPost(postNum, vis);
			
			Post p = UserDAO.getSpecificPost(postNum);
			request.setAttribute("Post", p);	
			String pUser = UserDAO.getPostUser(p.getUserId());
			request.setAttribute("pUser", pUser);
			
			//Now we need to update the view count
			UserDAO.upView(p.getPostId());
			url = "/ViewSpecificPost.jsp";
		}
		else
		{
			url = "/index.jsp";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		request.removeAttribute("ErrorMsgs");
		request.removeAttribute("User");
		String url = "";
		
		if (action.equalsIgnoreCase("register"))
        {
			//Get the user inputed data
			String username = request.getParameter("idusername");
			String password = request.getParameter("idpassword");
			String email = request.getParameter("idemail");
			
			//Default registration is 1, for general users
			//Admins would be manually added to the db
			int role = 1;
			//Create a new user based on input
			User user = new GeneralUser(username, password, email, role);
			
			//First we need to validate and if there are errors reload the page
			ErrorMsgs err = new ErrorMsgs();
			err.validateRegForm(username, password, email);
			request.setAttribute("ErrorMsgs", err);
			request.setAttribute("User", user);			
			if(err.isError())
			{
				url = "/RegisterForm.jsp";
			}
			else
			{
				UserDAO.addNewUser(user);	
	            url = "/index.jsp";
			}
        }
		else if(action.equalsIgnoreCase("login"))
		{
			//We want to first get the user input
			String username = request.getParameter("idusername");
			String password = request.getParameter("idpassword");
			
			//Next we want to check if the database for a match
			//it will return a boolean value for response
			boolean code = UserDAO.loginUser(username, password);
			
			//If there was a match we want to forward the next page
			//Else throw some error messages
			if(code)
			{
				//Now we want to create a user object to carry in the session
				User user = UserDAO.getUser(username, password);
				session.setAttribute("USER", user);
				List<Post> postList = new ArrayList<Post>();
				if(user.getRoleId() == 0)
				{
					postList = UserDAO.getAdminAllPost();
				}
				else
				{
					postList = UserDAO.getAllPost();
				}
				request.setAttribute("Post", postList);
				url = "/ViewAllRecipes.jsp";
			}
			else
			{
				ErrorMsgs err = new ErrorMsgs();
				err.loginFormError();
				request.setAttribute("ErrorMsgs", err);
				url = "/index.jsp";
			}
		}
		else if(action.equalsIgnoreCase("createPost"))
		{
			String title = request.getParameter("title");
			String desc = request.getParameter("desc");
			String inst = request.getParameter("instructions");
			int viewPostId = Integer.parseInt(request.getParameter("view"));
			User u = (User) session.getAttribute("USER");
			int user_id = u.getUserId();
			InputStream input = null; // input stream of the upload file
			Part filePart = request.getPart("photo");
			ErrorMsgs err = new ErrorMsgs();
			if (filePart != null) {
	            // prints out some information for debugging
	            if(filePart.getContentType().equals("image/jpeg") || filePart.getContentType().equals("image/png"))
	            {
	    			input = filePart.getInputStream();
	    			
	    			//First we need to validate and if there are errors and reload the page
	    			
	    			err.validatePostForm(title, desc, inst);
	    			request.setAttribute("ErrorMsgs", err);
	    			
	    			if(err.isError())
	    			{
	    				url = "/CreatePost.jsp";
	    			}
	    			else
	    			{
	    				//Now we need to query
	    				UserDAO.postRecipe(title, desc, inst, input, user_id, viewPostId);
	    				
	    				//Now we use the user_id to query
	    				List<Post> postList = new ArrayList<Post>();
	    				postList = UserDAO.getAllMyPosts(user_id);
	    				request.setAttribute("Post", postList);
	    				
	    				url = "/MyPosts.jsp";
	    			}
	            }
	            else
	            {
	            	err.imgError();
	            	request.setAttribute("ErrorMsgs", err);
	            	url = "/CreatePost.jsp";
	            }
	        }
		}
		else if(action.equalsIgnoreCase("ViewAllRecipes"))
		{
			User user = (User) session.getAttribute("USER");
			List<Post> postList = new ArrayList<Post>();
			if(user.getRoleId() == 0)
			{
				postList = UserDAO.getAdminAllPost();
			}
			else
			{
				postList = UserDAO.getAllPost();
			}	request.setAttribute("Post", postList);
			url = "/ViewAllRecipes.jsp";
		}
		else
		{
			url = "/index.jsp";
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
