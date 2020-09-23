package com.davjaime1.model;

public class User
{
	private String username;
	private String password;
	private String email;
	private int roleId;
	protected int userId;
	
	public User(String username, String password, String email, int roleId)
	{
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
	}

	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public int getRoleId()
	{
		return roleId;
	}

	public void setRoleId(int roleId)
	{
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString()
	{
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", roleId=" + roleId
				+ ", userId=" + userId + "]";
	}
}
