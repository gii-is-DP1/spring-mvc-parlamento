<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.1.0/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Listado de Organos</title>
</head>
<body>
	<h2>Órganos:</h2>
	<div class="container">
		<br />
		<c:if test="${mensaje != null}">
		<div class="alert alert-${tipomensaje}">
			<c:out value="${mensaje}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
		</div>
		</c:if>
	</div>
	<a href="/organos/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Crear Órgano</a>
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