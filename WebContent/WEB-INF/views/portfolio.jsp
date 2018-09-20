<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="portfolioModels.CurrentHoldings"%>

<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Portfolio Page</title>
<style>
body {
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
.money {
  content: "\0024";
}

table {
	font-family: arial, sans-serif;
	border-collapse: collapse;
	width: 60%;
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
		<a class="active" href="getportfolio">Portfolio</a> 
		<a href="gethistory">Transactions</a>
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
			<font color="navy">${username} your portfolio holdings as of <%= LocalDate.now() %></font>
		</h2>
	</div>

	<div style="padding-left: 40px">
		<table>

			<tr>
				<th>Stock Symbol</th>
				<th>Stock Name</th>
				<th>Shares</th>
				<th>Average Price</th>
				<th>Transaction Date</th>
				<th>Market Value</th>
			</tr>

			<c:forEach items="${stockholdings}" var="stock">
				<tr>
					<td><c:out value="${stock.stocksym}" /></td>
					<td><c:out value="${stock.stockname}" /></td>
					<td><c:out value="${stock.numshares}" /></td>
					<td>&#36;<c:out value="${stock.avgprice}" /></td>
					<td><c:out value="${stock.txdate}" /></td>
					<td>&#36;<c:out value="${stock.numshares*stock.avgprice}" /></td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<div style="padding-left: 450px">
		<h3>
			<font color="navy">total marketvalue:   &#36; ${totalmarketvalue}</font>
			<br><br>
			<font color="navy">cash on hand is:   &#36; ${cash}</font> 
			<br><br>
			<font color="navy">realized profit or loss:   &#36; ${gainloss}</font>
		</h3>

	</div>
</body>
</html>