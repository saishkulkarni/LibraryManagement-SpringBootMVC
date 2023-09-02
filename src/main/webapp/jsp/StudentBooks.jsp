<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="com.my.library.dto.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Librarian View Book</title>
</head>
<body>
	<%
	List<Book> books = (List<Book>) request.getAttribute("books");
	%>
	<h1 style="color: red">${neg}</h1>
	<h1 style="color: green">${pos}</h1>
	<form action="/student/fetchbook" method="post">
		<input type="text" name="name">
		<button>Search</button>
	</form>
	<br>
	<table border="1">
		<tr>
			<th>Book Picture</th>
			<th>Book Name</th>
			<th>Book Author</th>
			<th>Book Price</th>
			<th>Stock</th>
			<th>Borrow</th>
		</tr>
		<%
		for (Book book : books) {
		%>
		<tr>
			<th>
				<%
				String base64 = Base64.encodeBase64String(book.getPicture());
				%> <img height="100px" width="100px" alt="unknown"
				src="data:image/jpeg;base64,<%=base64%>">
			</th>
			<th><%=book.getName()%></th>
			<th><%=book.getAuthor()%></th>
			<th><%=book.getPrice()%></th>
			<th><%=book.getQuantity()%></th>
			<th><a href="/student/borrow/<%=book.getId()%>"><button>Borrow</button></a></th>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<a href="/jsp/StudentHome.jsp"><button>Back</button></a>
</body>
</html>