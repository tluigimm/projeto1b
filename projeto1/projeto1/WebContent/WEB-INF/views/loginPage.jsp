<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang = "us">
	<head>
		<link href="css/reset.css" rel="stylesheet">
		<link href="css/loginPage.css" rel="stylesheet">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		
		<title>Note Keeper</title>
		
	</head>
	<body class = 'body'>
		<div class = 'log_in'>
			<h1>Log-in</h1>
			
			<form action='login' method='post'>
				<div class = 'boxes'>
					<p class = 'p'> username: </p> <input class="input_frontPage" type='text' name='username'>
				</div>
				
				<div class = 'boxes'> 
					<p class = 'p'> password: </p> <input class="input_frontPage" type='password' name='password'> 
				</div>
			
				<input class='button' type="submit" value="log-in">
			</form>
			
		</div>
		
		<div class = 'log_on'>
			<h1>Log-on</h1>
			
			<form action='addUser' method='post' enctype="multipart/form-data">
				<div class = 'boxes'> 
					<p class = 'p'> username: </p> <input class="input_frontPage" type='text' name='username'>
				</div>
				<div class = 'boxes'> 
					<p class = 'p'> password: </p> <input class="input_frontPage" type='password' name='password1'>
				</div>
				<div class = 'boxes'> 
					<p class = 'p'> confirm <br > password: </p> <input class="input_frontPage" type='password' name='password2'>
				</div>
			
				<input class='button' type="submit" value="log-on">
			</form>
			
		</div>
	</body>
</html>
