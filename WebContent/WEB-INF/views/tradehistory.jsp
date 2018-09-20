<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Trade History Page</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/styling.css"/>">
<style>
<
style>body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #333;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #4CAF50;
	color: white;
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 50%;
}

td, th {
	border: 1px solid black;
	text-align: left;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #dddddd;
}
</style>


</head>
<body>



	<div class="topnav">
		<a href="home">Atrade Home</a> 
		<a href="settradepage">Trade</a> 
		<a href="getportfolio">Portfolio</a> 
		<a class="active" href="gethistory">Transactions</a>
		<a href="viewcash">Cash Management</a>
		<a href="Logout">Logout</a> 
		<a href="register">Register</a>
	</div>


	<div style="padding-left: 16px">
		<h2>
			<font color="blue">Welcome to Atrade - A Place to Trade Stocks</font>
		</h2>
	</div>

	<div style="padding-left: 40px">
		<h2>
			<font color="navy">${username} your trade history as of <%=LocalDate.now()%></font>
		</h2>
	</div>

	<div style="padding-left: 40px">
		<table>

			<tr>
				<th>Stock Symbol</th>
				<th>Stock Name</th>
				<th>Shares</th>
				<th>Order Type</th>
				<th>Transaction Price</th>
				<th>Transaction Date</th>
			</tr>

			<c:forEach items="${historytrades}" var="history">
				<tr>
					<td><c:out value="${history.stocksym}" /></td>
					<td><c:out value="${history.stockname}" /></td>
					<td><c:out value="${history.numshares}" /></td>
					<td><c:out value="${history.ordertype}" /></td>
					<td>&#36;<c:out value="${history.price}" /></td>
					<td><c:out value="${history.txdate}" /></td>
				</tr>
			</c:forEach>

		</table>
	</div>

</body>
</html>