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
	<%@ include file="Header.jsp" %> 
	<h2>Post a new recipe</h2>
    <form method="post" action="Controller?action=createPost" enctype="multipart/form-data">
        <table>
            <tr>
                <td>Recipe Title: </td>
                <td><input type="text" name="title" size="50"/></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><textarea name="desc" cols="50" rows="6"></textarea></td>
            </tr>
            <tr>
                <td>Instructions: </td>
                <td><textarea name="instructions" cols="50" rows="10"></textarea></td>
            </tr>
            <tr>
                <td>Recipe Photo: </td>
                <td><input type="file" name="photo" size="100"/></td>
            </tr>
            <tr>
            	
                <td>
                	<label for="1">Public</label><br>
                	<input type="radio" id="publicid" name="view" value="1">
                </td>
                <td>
                	<label for="0">Private</label><br>
                	<input type="radio" id="privateid" name="view" value="0">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>