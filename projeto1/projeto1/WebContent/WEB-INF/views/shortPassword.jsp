<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<link href="css/reset.css" rel="stylesheet">
		<link href="css/assignmentsPage.css" rel="stylesheet">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		
		<title>Note Keeper</title>
		
	</head>

	<body class='body'>
		<div class = 'title'>
			<h1> Password too short </h1> 
			<form action="returnLogin" method='post'>
				<input class='button' type='submit' value='exit'>
			</form>
		</div>
	</body>
</html>