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
		<%@ include file="Header.jsp" %>
		<div class="main">
		   	<table style="width:1000px; margin-left:auto;margin-right:auto">
	   			<c:forEach items="${Post}" var="item">
	   				<tr><td><a href="Controller?action=ViewSpecificPost&postNum=${item.postId}"><img src="data:image/jpg;base64,${item.photo}" width="240" height="300"/></a></td></tr>
					<tr><td>Title: <c:out value="${item.title}" /></td></tr>
					<tr><td>Description: <c:out value="${item.description}" /></td></tr>
		   		   	<tr><td><a href="Controller?action=ViewSpecificPost&postNum=${item.postId}">Read More...</a></td></tr>
		   		</c:forEach>	   		
	   		</table>
   		</div>
	</body>
</html>