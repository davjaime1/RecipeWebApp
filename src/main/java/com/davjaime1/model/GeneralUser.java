package com.davjaime1.model;

public class GeneralUser extends User
{

	public GeneralUser(String username, String password, String email, int role, int userId)
	{
		super(username, password, email, role);
		this.userId = userId;
	}
	
	public GeneralUser(String username, String password, String email, int role)
	{
		super(username, password, email, role);
	}
}
