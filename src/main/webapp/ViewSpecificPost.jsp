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
		<h2 class="main"><c:out value="${Post.title}" /></h2>
		<table style="width:100%; margin-left:auto;margin-right:auto">
			<tr><td style="text-align: center;"><img src="data:image/jpg;base64,${Post.photo}" style="max-width:45%; max-height:35%;"/></td></tr>
			<tr><td><b>Description </b> <br><c:out value="${Post.description}" /></td></tr>
			<tr><td style="white-space: pre-wrap;"><b>Instructions </b><br><c:out value="${Post.instructions}" /></td></tr>
			<tr><td><br>Submitted By: <c:out value="${pUser}" /></td></tr>
			<tr><td>Views: <c:out value="${Post.views}" /></td></tr>
			<c:choose>
				<c:when test = "${(Post.userId == USER.userId) or (USER.roleId == 0)}">
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
			         <tr><td><a href="Controller?action=changeVis&postNum=${Post.postId}&vis=${Post.viewId}">Change Post Visibility</a></td></tr>
			         <tr><td><a href="Controller?action=delete&postNum=${Post.postId}">Delete Post</a></td></tr>
			 	</c:when>
	         </c:choose>
	   	</table>
   	</div>
</body>
</html>