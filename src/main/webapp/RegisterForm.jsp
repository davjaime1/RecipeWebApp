<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form name="loginform" action="Controller?action=register" method="post">
		<table>
			<tr>
				<td> User Name: </td>
				<td> <input name="idusername" value="<c:out value='${User.username}'/>"  type="text" maxlength="15"> </td>
				<td> <input name="userIDerror"  value="<c:out value='${ErrorMsgs.usernameError}'/>" type="text" style ="width: 500px" disabled="disabled"> </td>
			</tr>

			<tr>
				<td> Password: </td>
				<td> <input name="idpassword" value="<c:out value='${User.password}'/>"  type="password" maxlength="15"> </td>
				<td> <input name="passIDerror"  value="<c:out value='${ErrorMsgs.passwordError}'/>" type="text" style ="width: 500px" disabled="disabled"> </td>
			</tr>
			<tr>
				<td> Email: </td>
				<td> <input name="idemail" value="<c:out value='${User.email}'/>"  type="text" maxlength="25"> </td>
				<td> <input name="emailIDerror"  value="<c:out value='${ErrorMsgs.emailError}'/>" type="text" style ="width: 500px" disabled="disabled"> </td>
			</tr>
		</table>
		<input name="submit" type="submit" value="Register">
	</form>
</body>
</html>