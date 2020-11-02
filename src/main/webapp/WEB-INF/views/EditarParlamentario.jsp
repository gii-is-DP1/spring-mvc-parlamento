<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Editar Parlamentario</title>
</head>
<body>
	<h2>Editar Parlamentario:</h2>
	<mvc:form modelAttribute="parlamentario" action="/parlamentarios/save">
		<table>
			<tr>
				<td><mvc:label path="id">ID</mvc:label></td>
				<td><mvc:input path="id" readOnly="true"/></td>
			</tr>
			<tr>
				<td><mvc:label path="nombre">Nombre:</mvc:label></td>
				<td><mvc:input path="nombre" /></td>
			</tr>
			<tr>
				<td><mvc:label path="organos">Miembro de:</mvc:label></td>
				<td>
					<mvc:select path="organos" multiple="multiple"> 
     					<mvc:options items="${todosOrganos}" itemValue="id" itemLabel="descripcion"></mvc:options>
     				</mvc:select>
     			</td>
			</tr>
			<tr>
				<td><a href="/parlamentarios" class="btn btn-secondary">Cancelar</a></td>
				<td><input type="submit" value="Guardar" class="btn btn-primary"/></td>
			</tr>
		</table>
	</mvc:form>
</body>
</html>