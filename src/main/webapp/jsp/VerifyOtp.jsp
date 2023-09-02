<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>
<body>
	<h1 style="color: red">${neg}</h1>
	<h1 style="color: green">${pos}</h1>
	<form action="/librarian/signup/${id}" method="post">
		Enter OTP: <input type="text" name="otp">
		<button>Submit</button>
	</form>
	<br>
	<a href="/librarian/signup"><button>Back</button></a>
</body>
</html>