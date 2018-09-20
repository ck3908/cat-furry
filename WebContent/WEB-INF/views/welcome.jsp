<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>this is the welcome view page</title>
</head>
<body>
	
	<h1>${message}</h1>
	
	<button onclick="getprice">getprice</button>
	
	<form action="validate" method="POST">

		<p>
			<select name = "symbol">
				<option>MSFT</option>
				<option>AAPL</option>
				<option>FB</option>
				<option>BA</option>
				<option>C</option>
				<option>GOOG</option>
				<option>NVDA</option>
				<option>NFLX</option>
				<option>GE</option>
				<option>WMT</option>									
			</select>
		</p>
		
		
		
		<h1> ${stockprice}</h1>
		
		<p>
			<label>Action</label> <select>
				<option>Buy</option>
				<option>Sell</option>
			</select>
		</p>
		<p>
			<label>Quantity</label> <input type="number"
				value="numshares">
		</p>
		

		<p>
			<button>Submit</button>
		</p>
	</form>


<a href="home"> home page </a>

</body>
</html>