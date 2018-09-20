<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>trade stocks</title>
</head>
<body>

	<h1>${message}</h1>

	<c:forEach items = "${listsymbols}" var = "eachitem"><p>${eachitem.stocksym}</p> </c:forEach>


	<h1>${stockprice}</h1>

<a href="login">User Login</a>   <!-- replace with href=login or use settradepage for temp fix-->
<br>
<br>
<a href="${pageContext.servletContext.contextPath}/register">Registration</a>

</body>
</html>