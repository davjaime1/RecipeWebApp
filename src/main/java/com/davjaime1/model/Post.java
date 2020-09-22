package com.davjaime1.model;

public class Post
{
	private String title;
	private String description;
	private String instructions;
	private String userId;
	
	@Override
	public String toString()
	{
		return "Post [title=" + title + ", description=" + description + ", instructions=" + instructions + ", userId="
				+ userId + ", photo=" + photo + "]";
	}
	
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	private String photo;
	
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getInstructions()
	{
		return instructions;
	}
	public void setInstructions(String instructions)
	{
		this.instructions = instructions;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
}
