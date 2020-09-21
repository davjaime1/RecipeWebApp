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
		/*if (action.equalsIgnoreCase("register"))
        {
        
        }*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		session.removeAttribute("ErrorMsgs");
		session.removeAttribute("User");
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
			System.out.println(err);
			
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
			url = "/ViewAllRecipes.jsp";
		}
		else
		{
			
		}
        getServletContext().getRequestDispatcher(url).forward(request, response);
		doGet(request, response);
	}

}
