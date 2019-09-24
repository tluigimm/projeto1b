<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link href="css/reset.css" rel="stylesheet">
		<link href="css/editAssignment.css" rel="stylesheet">
		<title>edit assignment</title>
	</head>
	<body class=body>
		<%@ page import='java.util.*, mvc.model.*' %>
		<%  
			int asgId = (int) session.getAttribute("asgId");
		%>
		
		<h1 class='title'>Edit your assignment</h1>
		<form action='editAsg' method='post' class='form'>
			<input type='hidden' value=<%= asgId  %> name='asgId'>
			<div class='box'>
				<p>asignment: <input type='text' name='note'></p>
				<p>date: <input type='text' name='date'></p>
			</div>
			
			<input type='submit' value='submit changes'>
			
		</form>

	</body>
</html>