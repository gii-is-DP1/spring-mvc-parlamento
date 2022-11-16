<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Statement for Customer</title>
</head>
<body>
	<h2>Rental record for <c:out value="${customer.name}"/></h2>
	<div class="container">
		<br />
		<c:if test="${message != null}">
		<div class="alert alert-${messageType}">
			<c:out value="${message}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">�</a>
		</div>
		</c:if>
	</div>

	<table class="table table-striped">
		<tr>
			<th>Title</th>
			<th>Amount</th>			
		</tr>
		<c:forEach items="${customer.rentals}" var="rental">
			<tr>
				<td><c:out value="${rental.movie.title}"/></td>
				<td><c:out value="${rentalInfo.get(rental)}"/></td>				
			</tr>
		</c:forEach>
	</table>


	<div>
	Amount owed is <c:out value="${totalAmount}"/> 
	</div>
	<div>
	You earned <c:out value="${earnedPoints}"/> frequent renter points 
	</div>

</body>
</html>