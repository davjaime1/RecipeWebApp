package com.davjaime1.model;

public class Post
{
	private int postId;
	private String title;
	private String description;
	private String instructions;
	private String userId;
	private int viewId;
	private int views;
	
	public int getViews()
	{
		return views;
	}
	public void setViews(int views)
	{
		this.views = views;
	}
	public void setViewId(int viewId)
	{
		this.viewId = viewId;
	}
	public int getViewId()
	{
		return viewId;
	}
	@Override
	public String toString()
	{
		return "Post [postId=" + postId + ", title=" + title + ", description=" + description + ", instructions="
				+ instructions + ", userId=" + userId + ", viewId=" + viewId + ", views=" + views + ", photo=" + photo
				+ "]";
	}
	public int getPostId()
	{
		return postId;
	}
	public void setPostId(int postId)
	{
		this.postId = postId;
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
