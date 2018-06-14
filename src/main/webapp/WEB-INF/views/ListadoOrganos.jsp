<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Listado de Organos</title>
</head>
<body>
	<h2>�rganos:</h2>
	<div class="container">
		<br />
		<div class="alert alert-success">
			<strong>Ok!</strong> Funciona!
			<a href="#" class="close" data-dismiss="alert" aria-label="close">�</a>
		</div>
	</div>
	<table class="table table-striped">
		<tr>
			<th>Abreviatura</th>
			<th>Descripcion</th>
			<th>Orden</th>
			<th>Acciones</th>
		</tr>
		<c:forEach items="${organos}" var="organo">
			<tr>
				<td>${organo.abreaviatura}</td>
				<td>${organo.descripcion}</td>
				<td>${organo.orden}</td>
				<td><a href="/organos/edit/${organo.id}" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>&nbsp;<a href="/organos/delete/${organo.id}"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>