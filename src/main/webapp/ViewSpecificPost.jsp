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
	<table >
		<tr><td><img src="data:image/jpg;base64,${Post.photo}" width="240" height="300"/></td></tr>
		<tr><td>Title: <c:out value="${Post.title}" /></td></tr>
		<tr><td>Description: <c:out value="${Post.description}" /></td></tr>
		<tr><td>Instructions: <pre><c:out value="${Post.instructions}" /></pre></td></tr>
   	</table>
</body>
</html>