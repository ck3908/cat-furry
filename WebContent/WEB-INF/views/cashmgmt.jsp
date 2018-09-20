<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cash Managment</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/styling.css"/>">
<style>
input[type=text], select {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=password], select {
	width: 20%;
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

input[type=number], select {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.insidediv {
	background-color: #f2f2f2;
	padding: 20px;
}

.button {
	background-color: #4CAF50; /* Green */
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 20px;
	cursor: pointer;
}

.btn2 {
	font-size: 13pt;
	color: Navy;
	background-color: White;
	border: 2px solid #336600;
	padding: 20px;
	border-radius: 12px;
	margin: 8px 112px;
}

label {
	/* To make sure that all labels have the same size and are properly aligned */
	display: inline-block;
	width: 90px;
	text-align: right;
}
</style>
</head>
<body>

	<div class="topnav">
		<a href="home">Atrade Home</a> 
		<a href = "settradepage">Trade</a> 
		<a href = "getportfolio">Portfolio</a> 
		<a href = "gethistory">Transactions</a>
		<a class="active" href="viewcash">Cash Management</a>
		<a href ="logout">Logout</a> 
		<a href = "register">Register</a>
	</div>

	<div style="padding-left: 16px">
		<h2>
			<font color="blue">Welcome ${username} to Atrade - A Place to Trade Stocks</font>
		</h2>
	</div>
	
		<div style="padding-left: 30px">
		<h3>
			<br>
			<br>
			<font color="black"> ${message}</font>
		</h3>
	</div>
	
	<div style="padding-left: 30px">
		<h3>
			<br>
			<br>
			<font color="black"> You have $ ${cash} available in your cash account to trade</font>
			<br>
			<br>
			<font color="red"> Manage your cash below, you cannot withdraw more than you have</font>
		</h3>
	</div>
	
		<div class="insidediv">
		<form action="updatecashaccount" method="post">
			<!-- login controller -->			
			<label for="uname">User Name</label> 
			<input type="text" id="fname" value = ${username} name = "username" readonly> <br> 
			<label for="number">Withdrawal or Add Cash</label>
			<input type="number" id="num" name="changecash"> <br> 
			<input type="submit" value="Update Cash" />
		</form>
	</div>
	
	<br>
	<br>
	<input type="button" class="btn2"
			onclick="location.href='settradepage'" value="Start Trading">

</body>
</html>