<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="css/Project.css">
	<title>Dave's Recipe Anthology</title>
</head>
<body>
	<section class="start">
		<h1>Dave's Recipe Anthology</h1>
		<p> Welcome to Dave's Recipe Anthology!
			
			A project created so that I Dave, a young adult with no cooking skills can keep track of my favorite recipes.
			For too long I've found great recipes to try out only to lose the delicious meal due to lost or broken web links.
			
			Check out the recipes already posted and add your own!
		</p>
	</section>
	<div class="start">
		<h2>Sign In!</h2>
			<form name="loginform" action="Controller?action=login" method="post" class="start" style="display:inline;">
				<c:choose>
					<c:when test = "${!empty ErrorMsgs.loginError}">
						<input name="userIDerror"  value="<c:out value='${ErrorMsgs.loginError}'/>" type="text" style ="width: 250px" disabled="disabled">
					</c:when>
			    </c:choose>
			    <div align="center">
				<table class="login">
					<tr>
						<td> Username: </td>
						<td> <input name="idusername" value="<c:out value='${User.username}'/>"  type="text" maxlength="15"> </td>
					</tr>
		
					<tr>
						<td> Password: </td>
						<td> <input name="idpassword" value="<c:out value='${User.password}'/>"  type="password" maxlength="15"> </td>
					</tr>
				</table>
				</div>
				<input name="submit" type="submit" value="Login" style="margin-left:60px;">
			</form>
			<form name="registerform" action="Controller" method="get" class="start" style="display:inline;">
				<input id="actionid" type="hidden" name="action" value="Register" />
				<input type="submit" value="Register">
			</form>
	</div>
</body>
</html>
