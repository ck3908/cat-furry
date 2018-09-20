<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>trade page</title>
</head>
<body>

<h1>${message}</h1>

<!--  
	<form action="getprice" method="POST">
		<input type="submit" name="getprice" value="getprice">
	</form>
	
	<input type="button"  onclick="location.href='getprice'" value="Submit" >

-->


	<form action="getprice" method="POST">

		<p>
			<select id = "mySelect" name="symbol">
				<option value = "MSFT">MSFT</option>
				<option value = "AAPL">AAPL</option>
				<option value = "FB">FB</option>
				<option value = "BA">BA</option>
				<option value = "C">C</option>
				<option value = "GOOG">GOOG</option>
				<option value = "NVDA">NVDA</option>
				<option value = "NFLX">NFLX</option>
				<option value = "GE">GE</option>
				<option value = "WMT">WMT</option>
			</select>
		</p>

		<p>
			<button name = "pricebutton">Submit</button>
		</p>
	</form>

	<h3> the stock symbol selected is ${stocksym} </h3>
	<h3> the current price is ${stockprice}</h3>
		
	<h3> What would you like to do at the current price</h3>	
	<form action="validate" method="POST">
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
			<button name = "validatebutton">Submit</button>
		</p>
	</form>


<a href="home"> home page </a>


</body>
</html>