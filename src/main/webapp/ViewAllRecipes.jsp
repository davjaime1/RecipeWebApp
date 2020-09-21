<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>All Recipes</title>
   	</head>
   	<body>
   		${USER}
   		<br>
   		Here we will put all the recipes.
   		<br>
   		Maybe add an alphabetical
   		<br>
   		Link for new post
   		<a href="Controller?action=CreateNewPostPage" target="_top" style="color:blue"><span>Create a new post</span></a>
   	</body>
</html>