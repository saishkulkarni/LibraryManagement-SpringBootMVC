<%@page import="com.my.library.dto.BookRecord"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Book Borrowing History</title>
</head>
<body>
	<%
	List<BookRecord> list = (List<BookRecord>) request.getAttribute("list");
	%>
	<table border="1">
		<tr>
			<th>Book Name</th>
			<th>Issue Date</th>
			<th>Return Date</th>
			<th>Fine</th>
		</tr>

		<%
		for (BookRecord record : list) {
		%>
		<tr>
			<th><%=record.getBook().getName()%></th>
			<th><%=record.getIssueDate()%></th>
			<th><%if(record.getReturnDate()==null)%>Yet to Return<%else%><%=record.getReturnDate() %></th>
			<th><%=record.getFine()%></th>
		</tr>
		<%
		}
		%>
	</table>
	<a href="/jsp/StudentHome.jsp"><button>Back</button></a>
</body>
</html>