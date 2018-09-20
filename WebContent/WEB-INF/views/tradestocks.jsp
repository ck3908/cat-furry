<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Trade Page</title>
<link rel="stylesheet"
	href="<c:url value="/resources/css/styling.css"/>">
<style>

input[type=text], select {
	width: 20%;
	padding: 12px 20px;
	margin: 8px 0px;
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
	margin: 8px 0;
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
  <a class = "active" href="settradepage">Trade</a> 
  <a href ="getportfolio">Portfolio</a>
  <a href ="gethistory">Transactions</a>
  <a href ="viewcash">Cash Management</a>
  <a href ="logout">Logout</a>
  <a href ="register">Register</a> 
</div>

<div style="padding-left:16px">
  <h2><font color="blue">Welcome to Atrade - A Place to Trade Stocks</font></h2>
</div>

<!--  	<h1>${message}</h1> -->
<div style="padding-left: 50px">
	<form:form action="getdetail" method="POST" modelAttribute = "listsymbols">
		Select a Stock Symbol: <select name="symbol">
			<c:forEach items = "${listsymbols}" var="eachitem">
				<option value="${eachitem.stocksym}">
					${eachitem.stocksym}</option>
			</c:forEach>
		</select> <br />
		<br /> <input type="submit" style="font-size:13pt;color:Navy;background-color:silver;border:2px solid #336600;padding:10px" value="Get Price" />
	</form:form>

	<h1>${stock_sym} - ${stock_name}</h1>
	<h1>${stock_price}</h1>
	

	<form method="GET" action="preparetrade">
		<input type="submit" style="font-size:13pt;color:white;background-color:silver;border:2px solid #336600;padding:10px" value="Trade"
			${active eq 'on' ? '' : 'disabled' } />
	</form>

</div>

</body>
</html>