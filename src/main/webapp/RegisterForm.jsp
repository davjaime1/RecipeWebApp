<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/Project.css">
<title>Dave's Recipe Anthology</title>
</head>
<body>
	<section class="start">
		<h1>Dave's Recipe Anthology</h1>
	</section>
	<div class="start">
		<h2>Create Account</h2>
		<form name="loginform" action="Controller?action=register" method="post" class="start">
			<table class="login">
				<tr>
					<td> User Name: </td>
					<td> <input name="idusername" value="<c:out value='${User.username}'/>"  type="text" maxlength="15"> </td>
					<c:choose>
						<c:when test = "${!empty ErrorMsgs.usernameError}">
							<input name="userIDerror"  value="<c:out value='${ErrorMsgs.usernameError}'/>" type="text" style =" background: transparent; border: none; width: 500px" disabled="disabled">
						</c:when>
			         </c:choose>
				</tr>
	
				<tr>
					<td> Password: </td>
					<td> <input name="idpassword" value="<c:out value='${User.password}'/>"  type="password" maxlength="15"> </td>
					<c:choose>
						<c:when test = "${!empty ErrorMsgs.passwordError}">
							<input name="passIDerror"  value="<c:out value='${ErrorMsgs.passwordError}'/>" type="text" style ="background: transparent; border: none; width: 500px" disabled="disabled">
						</c:when>
			        </c:choose>
				</tr>
				<tr>
					<td> Email: </td>
					<td> <input name="idemail" value="<c:out value='${User.email}'/>"  type="text" maxlength="25"> </td>
					<c:choose>
						<c:when test = "${!empty ErrorMsgs.emailError}">
							<input name="emailIDerror"  value="<c:out value='${ErrorMsgs.emailError}'/>" type="text" style ="background: transparent; border: none; width: 500px" disabled="disabled">
						</c:when>
			        </c:choose>
				</tr>
			</table>
			<input name="submit" type="submit" value="Register">
		</form>
	</div>
</body>
</html>