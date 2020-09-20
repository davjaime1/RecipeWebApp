<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<td> <input name="idusername" value=""  type="text" maxlength="15"> </td>
			</tr>
			<tr>
				<td> Password: </td>
				<td> <input name="idpassword" value=""  type="password" maxlength="15"> </td>
			</tr>
			<tr>
				<td> Email: </td>
				<td> <input name="idemail" value=""  type="text" maxlength="25"> </td>
			</tr>
		</table>
		<input name="submit" type="submit" value="Register">
	</form>
</body>
</html>