
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
	color: grey;
	  
}

input[type=number], select {
	width: 15%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	color: grey;
	  
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

.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 105px;
    cursor: pointer;
}

.button2 {
    background-color: white; 
    color: black; 
    border: 2px solid #008CBA;
    border-radius: 12px;
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
  <a href ="login">LogIn</a>
  <a class = "active" href ="register">Register</a> 
</div>

<div style="padding-left:16px">
  <h2><font color="blue">Welcome to Atrade - A Place to Trade Stocks</font></h2>
</div>

<div style="padding-left:16px">
  <h3>
			<font color="navy">Please Confirm your Information</font>
		</h3>
</div>


<div class="insidediv" style="padding-left: 16px">

	<form action = "registration" method = "POST">
	
		<div class = "sub_field">
		<label>Full Name: </label>
		<input type="text" id = "fullname" name = "fullname" value = ${fullname} readonly/>
		</div>
		
		<div class = "sub_field">
		<label> User Name: </label>
		<input type="text" id = "username" name = "username" value = ${username} readonly/>
		</div>
		
		<div class = "sub_field">
		<label> Password: </label>
		<input type="password" id = "pass" name = "password" value = ${password} readonly/>
		</div>
		
		<div class = "sub_field">
		<label> Email: </label>
		<input type="text" id = "email" name = "email" value = ${email} readonly/>
		</div>
		
		<div class = "sub_field">
		<label> Address: </label>
		<input type="text" id = "address" name = "address" value = ${address} readonly/> 
		</div> 
		
		<div class = "sub_field">
		<label> Telephone: </label>
		<input type="text" id = "telephone" name = "telephone" value = ${telephone} readonly/>
		</div>
	
		<div class = "sub_field">
		<label> Cash Deposit: </label>
		<input type="number" id = "cashdeposit" name = "cashdeposit" value = ${cashdeposit} readonly/>
		</div>
				
		
		<div class = "btn">

		<input type="submit" id = "submit" value = "Confirm" />
		</div>	
	
	</form>

</div>

<input type="button"  class = "button button2" onclick="location.href='home'" value="Cancel" >

</body>
</html>