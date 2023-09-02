<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1 style="color: green;">${pos}</h1>
	<h1 style="color: red;">${neg}</h1>
	<div style="margin-left: 450px; margin-top: 150px">
		<h1>Login Page</h1>
		<form action="/librarian" method="post">
			<fieldset style="width: 100px">
				<table>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email"></td>
					</tr>

					<tr>
						<td>Password:</td>
						<td><input type="password" name="password"></td>
					</tr>

					<tr>
						<td><button>Cancel</button></td>
						<td><button>Submit</button></td>
					</tr>


				</table>

				<br> <a href="/"><button type="button">Back</button></a>
			</fieldset>
		</form>

	</div>
</body>
</html>