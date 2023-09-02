<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Book</title>
</head>
<body>
	<h1 style="color: red">${neg}</h1>
	<h1 style="color: green">${pos}</h1>
	<h1>Edit Book Details</h1>
	<form action="/librarian/updatebook" method="post">
		<input type="text" name="id" value="${book.getId()}" hidden="">
		Name:<input type="text" name="name" value="${book.getName()}"><br>
		Author:<input type="text" name="author" value="${book.getAuthor()}"><br>
		Price:<input type="text" name="price" value="${book.getPrice()}"><br>
		Quantity:<input type="text" name="quantity"
			value="${book.getQuantity()}"><br>
		<button>Update</button>
		<button type="reset">Cancel</button>
	</form>
	<br>
	<a href="/jsp/LibrarianHome.jsp"><button>Back</button></a>
</body>
</html>