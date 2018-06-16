<%@page import="java.util.ArrayList"%>
<%@page import="com.japarejo.springmvc.model.entities.Parlamentario"%>
<%@page import="com.japarejo.springmvc.model.entities.Organo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"  href="/webjars/bootstrap/css/bootstrap.min.css" />
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>

<title>Listado de parlamentarios</title>
</head>
<body>
	<h2>Parlamentarios:</h2>
	<div class="container">
		<br />
		<c:if test="${mensaje != null}">
		<div class="alert alert-${tipomensaje}">
			<c:out value="${mensaje}"></c:out>
			<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
		</div>
		</c:if>
	</div>
	<a href="/parlamentarios/create"><span class="glyphicon glyphicon-plus sucess" aria-hidden="true"></span>Crear parlamentario</a>
	<table class="table table-striped">
		<tr>
			<th>Nombre</th>
			<th>Órganos</th>
			
			<th>Acciones</th>
		</tr>
		 <% ArrayList<Parlamentario> parls=((ArrayList<Parlamentario>)request.getAttribute("parlamentarios")); 
		 	for(Parlamentario p:parls){
		 	%>		
			<tr>
				<td><%=p.getNombre()%></td>				
				<td>Miembro de:
					<ul>
					<% for(Organo o:p.getOrganos()){ %>
						<li><%= o.getDescripcion()%></li>
					<% } %>
					</ul>
				</td>
				<td><a href="/parlamentarios/edit/<%=p.getId() %>" ><span class="glyphicon glyphicon-pencil warning" aria-hidden="true"></span></a>&nbsp;<a href="/parlamentarios/delete/<%=p.getId() %>"><span class="glyphicon glyphicon-trash alert" aria-hidden="true"></a> </td>
			</tr>
		<% } %>
	</table>
</body>
</html>