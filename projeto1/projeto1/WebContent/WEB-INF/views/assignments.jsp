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
		<%@ page import='java.util.*, mvc.model.*' %>
		<%  
			int userId = (int) session.getAttribute("userId");
			DAO dao = new DAO();
			String username = dao.getUsername(userId);
		%>
		<div class = 'title'>
			<h1> Assignments from <u><%= username %></u>: </h1> 
			<p class='p'>
				<form action="logout" method='post'>
					<input class='button' type='submit' value='exit'>
				</form>
			</p>
		</div>
		
		<div class='notes'>
			<div class='assignment'>
				<h2>Add assignment</h2>
				
				<form class='box' action='AddAssignment' method='post'>
				
					<p class = 'p'> Subject: </p> <input class='textInput' type='text' name='sub'>
					
					<p class = 'p'> Assignment: </p> <input class='textInput' id='assignmentsInput' type='text' name='note'>
				
					<p class = 'p'> Date: </p> <input class='textInput' type='text' name='date'>
					
					<input type="hidden" value=<%= userId %> name='userId' >
					
					<input class='button' id='submitButton' type="submit" value="add">
				</form>
			</div>
			
			<%
				List<Assignment> asgs = dao.getUserAssignments(userId);
				for (Assignment asg : asgs){
					System.out.println(asg.getId());
			%>
					
					<div class='assignment'>
					
						<p class = 'p'><%= asg.getSub() %></p>
						<p class = 'p'><%= asg.getNote() %></p>
						<p class = 'p'><%= asg.getDate() %></p>
						
						<div class='buttons'>
							<form action='RemoveAssignment' method='post'>
								<input type='hidden' value=<%= asg.getId() %> name='asgId'>
								<input class='button' type='submit' value='remove'>
							</form>
							<form action='moveEditAssignment' method='post'>
								<input type='hidden' value=<%= asg.getId() %> name='asgId'>
								<input class='button' type='submit' value='edit'>
							</form>
						</div>
					</div>
			<% } %>
		</div>
		
	</body>
</html>