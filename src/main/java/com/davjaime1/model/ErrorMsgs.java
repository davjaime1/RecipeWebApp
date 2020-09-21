package com.davjaime1.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorMsgs
{
	private String usernameError;
	private String passwordError;
	private String emailError;
	private String loginError;
	private boolean error;
	
	public ErrorMsgs()
	{
		this.error = false;
	}
	
	@Override
	public String toString()
	{
		return "ErrorMsgs [usernameError=" + usernameError + ", passwordError=" + passwordError + ", emailError="
				+ emailError + ", loginError=" + loginError + ", error=" + error + "]";
	}


	public void validateRegForm(String username, String password, String email)
	{
		String firstPattern = "^\\w{8,15}$";
		Pattern r = Pattern.compile(firstPattern);
		Matcher m = r.matcher(username);
		Matcher m2 = r.matcher(password);
		if(!m.find())
		{
			setUsernameError("Username must be between 8-15 and contain only letters, digits, or underscores");
			error = true;
		}
		else
		{
			setUsernameError("");
		}
		if(!m2.find())
		{
			setPasswordError("Password must be between 8-15 and contain only letters and or digits");
			error = true;
		}
		else
		{
			setPasswordError("");
		}
		
		if(email.equals(""))
		{
			setEmailError("Email cannot be emtpy");
			error = true;
		}
		else
		{
			setEmailError("");
		}
			
	}
	
	public void loginFormError()
	{
		setLoginError("Username or password is incorrect");
	}
	
	public boolean isError()
	{
		return error;
	}

	public void setError(boolean error)
	{
		this.error = error;
	}

	public String getUsernameError()
	{
		return usernameError;
	}

	public void setUsernameError(String usernameError)
	{
		this.usernameError = usernameError;
	}

	public String getPasswordError()
	{
		return passwordError;
	}

	public void setPasswordError(String passwordError)
	{
		this.passwordError = passwordError;
	}

	public String getEmailError()
	{
		return emailError;
	}

	public void setEmailError(String emailError)
	{
		this.emailError = emailError;
	}
	public String getLoginError()
	{
		return loginError;
	}

	public void setLoginError(String loginError)
	{
		this.loginError = loginError;
	}
}
