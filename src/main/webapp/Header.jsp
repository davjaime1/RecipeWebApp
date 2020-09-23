<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>

<form name="logoutform" action="Controller" method="get">
	<input id="actionid" type="hidden" name="action" value="logout" />
	<input type="submit" value="Logout">
</form>
<form name="myPostsform" action="Controller" method="get">
	<input id="actionid" type="hidden" name="action" value="myPosts" />
	<input type="submit" value="My Recepies">
</form>