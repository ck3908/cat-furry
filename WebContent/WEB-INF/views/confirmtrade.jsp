<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@page import="portfolioModels.StockTable"%>

<%@page import="portfolioModels.History"%>

<%@page import="java.time.LocalDate"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Confirm Trade</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/styling.css"/>">

<style>
input[type=text], select {
	width: 15%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=number], select {
	width: 15%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=password], select {
	width: 15%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=date], select {
	width: 15%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit] {
	width: 10%;
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin: 8px 92px;
	border: none;
	border-radius: 12px;
	cursor: pointer;
	font-size: 16px;
}

input[type=submit]:hover {
	background-color: #45a049;
}

.insidediv {
	background-color: #f2f2f2;
	padding: 20px;
}

.btn1 {
	font-size: 13pt;
	color: Navy;
	background-color: silver;
	border: 2px solid #336600;
	padding: 10px"
}

.btn2 {
	font-size: 13pt;
	color: Navy;
	background-color: PaleVioletRed;
	border: 2px solid #336600;
	padding: 10px;
	border-radius: 12px;
	margin: 8px 92px;
}

label {
	/* To make sure that all labels have the same size and are properly aligned */
	display: inline-block;
	width: 90px;
	text-align: right;
}

input[readonly] {
	background-color: rgb(235, 235, 228);
	cursor: default;
	text: rgb(84, 84, 84);
}
.hidden{
   visibility:hidden;
}
</style>
</head>
<body>

	<div class="topnav">
		<a href="home">Atrade Home</a> 
		<a class="active" href="settradepage">Trade</a>
		<a href="getportfolio">Portfolio</a> 
		<a href="gethistory">Transactions</a>
		<a href ="viewcash">Cash Management</a>
		<a href="logout">Logout</a> 
		<a href="register">Register</a>
	</div>

	<div style="padding-left: 16px">
		<h2>
			<font color="blue">Welcome to Atrade - A Place to Trade Stocks</font>
		</h2>
	</div>

	<%
		History trade = (History) session.getAttribute("tradedata");
	%>

	<div style="padding-left: 40px">
		<h2>
			<font color="navy">Confirm Trade</font>
		</h2>
	</div>


	<h1>${errormsg}</h1>

	<div class="insidediv" style="padding-left: 100px">
		<form:form action="executetrade" method="POST">
			<p>
				<label class = "hidden">User ID</label> <input type="hidden"
					value=<%=trade.getCustomerid()%> name="customerid" readonly>
			</p>
			<p>
				<label>Stock Name</label> <input type="text"
					value=<%=trade.getStockname()%> name="stockname" readonly>
			</p>
			<p>
				<label>Symbol</label> <input type="text"
					value=<%=trade.getStocksym()%> name="stocksym" readonly>
			</p>
			<p>
				<label>Price</label> <input type="number"
					value=<%=trade.getPrice()%> name="price" readonly>
			</p>
			<p>
				<label>Order Type</label> <input type="text"
					value=<%=trade.getOrdertype()%> name="ordertype" readonly>
			</p>

			<p>
				<label>Quantity</label> <input type="number"
					value=<%=trade.getNumshares()%> name="numshares" readonly>
			</p>

			<p>
				<label>Trade Date</label> <input type="date"
					value=<%=LocalDate.now()%> name="txdate" readonly>
			</p>

			<div style="padding-left: 40px">
				<h2>
					<font color="navy">total transaction value is ${txvalue}</font>
				</h2>
			</div>

			<div>
				<input type="submit"
					style="font-size: 13pt; color: Navy; background-color: silver; border: 2px solid #336600; padding: 10px"
					value="Confirm Trade" />
			</div>


		</form:form>

		<input type="button" class="btn2"
			onclick="location.href='settradepage'" value="Cancel Trade">

	</div>

</body>
</html>