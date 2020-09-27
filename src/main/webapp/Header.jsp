<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<link rel="stylesheet" href="css/Project.css">
<section class="start">
	<div>
		<h1>Dave's Recipe Anthology</h1>
		<form name="logoutform" action="Controller" method="get" class="start" style="display:inline;">
			<input id="actionid" type="hidden" name="action" value="logout" />
			<input type="submit" value="Logout">
		</form>
		<form name="allRecipesform" action="Controller" method="post" class="start" style="display:inline;">
			<input id="actionid" type="hidden" name="action" value="ViewAllRecipes" />
			<input type="submit" value="Most Viewed Recipes">
		</form>
		<c:choose>
			<c:when test = "${USER.roleId != 0}">
				<form name="myRecipesform" action="Controller" method="get" class="start" style="display:inline;">
					<input id="actionid" type="hidden" name="action" value="myPosts" />
					<input type="submit" value="My Recepies">
				</form>
				<form name="CreatePostform" action="Controller" method="get" class="start" style="display:inline;">
					<input id="actionid" type="hidden" name="action" value="CreateNewPostPage" />
					<input type="submit" value="Create a new post">
				</form>
			</c:when>
	    </c:choose>
	</div>
</section>
