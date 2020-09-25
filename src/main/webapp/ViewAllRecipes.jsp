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
   		<%@ include file="Header.jsp" %> 
   		<div class="main">
	   		<h2 class="main">Most Viewed Recipes</h2>
	   		<table class="posts">
	   			<c:forEach items="${Post}" var="item">
	   				<tr><td><a href="Controller?action=ViewSpecificPost&postNum=${item.postId}"><img src="data:image/jpg;base64,${item.photo}" style="max-width:400px; max-height:300px;"/></a></td></tr>
					<tr><td>Title: <c:out value="${item.title}" /></td></tr>
					<tr><td>Description: <c:out value="${item.description}" /></td></tr>
		   		   	<tr><td style="padding-bottom: 50px"><a href="Controller?action=ViewSpecificPost&postNum=${item.postId}">Read More...</a></td></tr>
		   		</c:forEach>	   		
	   		</table>
   		</div>
   	</body>
</html>