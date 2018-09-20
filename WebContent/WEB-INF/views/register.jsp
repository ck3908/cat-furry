
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Registration Page</title>
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
		<a "settradepage">Trade</a> 
		<a "getportfolio">Portfolio</a> 
		<a "gethistory">Transactions</a>
		<a "viewcash">Cash Management</a>
		<a href="login">LogIn</a> 
		<a class="active" href="register">Register</a>
	</div>

	<div style="padding-left: 16px">
		<h2>
			<font color="blue">Welcome to Atrade - A Place to Trade Stocks</font>
		</h2>
	</div>

	<div style="padding-left: 16px">
		<h3>
			<font color="navy">Please Register</font>
		</h3>
	</div>

	<div class="insidediv" style="padding-left: 16px">
		<form:form action="customerInfo" method="POST"
			modelAttribute="customerkey">


			<div class="sub_field">
				<label> Full Name: </label>
				<form:input type="text" id="fullname" path="fullname" />
			</div>

			<div class="sub_field">
				<label> User Name:</label>
				<form:input type="text" id="username" path="username" />
				<form:errors path="username" />
			</div>

			<div class="sub_field">
				<label> Password: </label> <input type="password" id="pass"
					name="password" />
			</div>

			<div class="sub_field">
				<label> Email: </label>
				<form:input type="text" id="email" path="email" />
				<form:errors path="email" />
			</div>

			<div class="sub_field">
				<label> Address: </label>
				<form:input type="text" id="address" path="address" />
			</div>

			<div class="sub_field">
				<label> Telephone: </label>
				<form:input type="text" id="telephone" path="telephone" />
			</div>

			<div class="sub_field">
				<label> Cash Deposit: </label>
				<form:input type="number" id="cashdeposit" path="cashdeposit" />
			</div>


			<div class="btn">

				<input type="submit" id="submit" value="Register" />
			</div>


		</form:form>



	</div>



</body>
</html>


