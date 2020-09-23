<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<title>All Recipes</title>
   	</head>
   	<body style="background-color:tan;">
   		<%@ include file="Header.jsp" %> 
   		<h2>Most Viewed Recipes</h2>
   		<table style="width:1000px; margin-left:auto;margin-right:auto">
   			<c:forEach items="${Post}" var="item">
   				<tr><td><a href="Controller?action=ViewSpecificPost&postNum=${item.postId}"><img src="data:image/jpg;base64,${item.photo}" width="240" height="300"/></a></td></tr>
				<tr><td>Title: <c:out value="${item.title}" /></td></tr>
				<tr><td>Description: <c:out value="${item.description}" /></td></tr>
	   		   	<tr><td style="padding-bottom: 50px"><a href="Controller?action=ViewSpecificPost&postNum=${item.postId}">Read More...</a></td></tr>
	   		</c:forEach>	   		
   		</table>
   	</body>
</html>