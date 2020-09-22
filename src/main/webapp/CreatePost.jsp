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
	<h1>Post a new recipe</h1>
	Create a post page
	<br>
	A new form for uploading an image
	<br>
	and a block of input for instructions
    <form method="post" action="Controller?action=createPost" enctype="multipart/form-data">
        <table>
            <tr>
                <td>Recipe Title: </td>
                <td><input type="text" name="title" size="50"/></td>
            </tr>
            <tr>
                <td>Description: </td>
                <td><input type="text" name="desc" size="50"/></td>
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
                <td colspan="2">
                    <input type="submit" value="Save">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>