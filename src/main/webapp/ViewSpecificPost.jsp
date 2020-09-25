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
			<tr><td><img src="data:image/jpg;base64,${Post.photo}" style="max-width:400px; max-height:300px;"/></td></tr>
			<tr><td>Title: <c:out value="${Post.title}" /></td></tr>
			<tr><td>Description: <c:out value="${Post.description}" /></td></tr>
			<tr><td>Instructions: <pre  style="overflow: scroll;width:1000px;"><c:out value="${Post.instructions}" /></pre></td></tr>
			<tr><td>Submitted By: <c:out value="${pUser}" /></td></tr>
			<tr><td>Views: <c:out value="${Post.views}" /></td></tr>
			<c:choose>
				<c:when test = "${Post.userId == USER.userId}">
					<tr><td>Post Visibility: 
						<c:choose>
			         		<c:when test = "${Post.viewId == 0}">
			            		Private
			         		</c:when>
			         		<c:when test = "${Post.viewId == 1}">
			            		Public
			         		</c:when>
			         	</c:choose>
			         </td></tr>
			 	</c:when>
	         </c:choose>
	   	</table>
   	</div>
</body>
</html>