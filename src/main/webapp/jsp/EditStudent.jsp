<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Student Details</title>
</head>
<body>
	<h1 style="color: red">${neg}</h1>
	<h1 style="color: green">${pos}</h1>
	<form action="/student/update" method="post">
		<fieldset style="width: 40vw">
			<legend>Student Update</legend>
			<input type="text" name="id" value="${student.getId()}" hidden="">
			<table>

				<tr>
					<th>Name:</th>
					<th><input type="text" name="name" value="${student.getName()}"></th>
				</tr>

				<tr>
					<th>Email:</th>
					<th><input type="email" name="email" value="${student.getEmail()}" readonly="readonly"></th>
				</tr>

				<tr>
					<th>Mobile:</th>
					<th><input type="number" name="mobile" value="${student.getMobile()}"></th>
				</tr>

				<tr>
					<th>Password:</th>
					<th><input type="password" name="password" required="required"></th>
				</tr>

				<tr>
					<th><label>DOB: </label></th>
					<th><input type="date" name="date" value="${student.getDob()}"></th>
				</tr>

				<tr>
					<th><label for="gender">Gender: </label></th>
					<th><input type="radio" name="gender" value="male" id="gender">Male
						<input type="radio" name="gender" value="female" id="gender">Female
					</th>
				</tr>

				<tr>
					<th><button>Update</button></th>
					<th><button type="reset">Cancel</button></th>
				</tr>

			</table>
			<br> <a href="/jsp/StudentHome.jsp"><button type="button">Back</button></a>
		</fieldset>
	</form>
</body>
</html>