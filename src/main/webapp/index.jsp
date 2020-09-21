<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<body>
<h1>Recipe Website Title</h1>
	<form name="loginform" action="Controller?action=login" method="post">
		<input name="userIDerror"  value="<c:out value='${ErrorMsgs.loginError}'/>" type="text" style ="background-color: white; border: none; width: 500px" disabled="disabled">
		<table>
			<tr>
				<td> User Name: </td>
				<td> <input name="idusername" value="<c:out value='${User.username}'/>"  type="text" maxlength="15"> </td>
			</tr>

			<tr>
				<td> Password: </td>
				<td> <input name="idpassword" value="<c:out value='${User.password}'/>"  type="password" maxlength="15"> </td>
			</tr>
		</table>
		<input name="submit" type="submit" value="Login">
	</form>
<form name="registerform" action="RegisterForm.jsp" method="get">
<input type="submit" value="Register">
</form>
</body>
</html>
