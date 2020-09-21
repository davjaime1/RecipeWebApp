package com.davjaime1.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.davjaime1.dao.UserDAO;
import com.davjaime1.model.Admin;
import com.davjaime1.model.ErrorMsgs;
import com.davjaime1.model.User;

/**
 * Servlet implementation class Controller
 */
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
		if (action.equalsIgnoreCase("CreateNewPostPage"))
        {
			url="/CreatePost.jsp";
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
			User user = new Admin(username, password, email, role);
			
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
		else
		{
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
