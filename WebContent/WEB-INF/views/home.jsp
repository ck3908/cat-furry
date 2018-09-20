<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home Page</title>
<link rel="stylesheet" href = "<c:url value="/resources/css/styling.css"/>">

<style>

.button {
    background-color: #4CAF50; /* Green */
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}

.button1 {
    background-color: white; 
    color: black; 
    border: 2px solid #4CAF50;
    border-radius: 12px;
}

.button2 {
    background-color: white; 
    color: black; 
    border: 2px solid #008CBA;
    border-radius: 12px;
}

.center {
    margin: auto;
    width: 30%;
    text-align: center;
    border: 3px solid #73AD21;
    padding: 10px;
}

.textalign {
	text-align:center;
	font-style: italic;
	font-weight: bold;

}


</style>


</head>
<body>

<div class="topnav">
  <a class="active" href="#home">Atrade Home</a>
  <a "settradepage">Trade</a> 
  <a "getportfolio">Portfolio</a>
  <a "gethistory">Transactions</a>
  <a "viewcash">Cash Management</a>
  <a href ="login">LogIn</a>
  <a href ="register">Register</a>
  
</div>

<div style="padding-left:16px">
  <h2><font color="blue">Welcome to Atrade - A Place to Trade Stocks</font></h2>
</div>

<br>

<div class ="textalign"> Join Us For a Trading Session</div>

<br>

<div class = "center">
<input type="button"  class = "button button1" onclick="location.href='login'" value="  Login  " >
<br>
<p> or </p>
<br>
<input type="button"  class = "button button2" onclick="location.href='register'" value="Register" >
</div>


</body>
</html>