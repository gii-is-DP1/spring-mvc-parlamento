<%@ page language="java" contentType="text/ht ml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
				<td><mvc:label path="organos">Nombre:</mvc:label></td>
				<td>
					<mvc:select path="organos" multiple="multiple" 
     							items="${organos}" itemValue="id"
     							itemLabel="descripcion" class="multiselect">
     				</mvc:select>
     			</td>
			</tr>
			<tr>
				<td><a href="/palamentarios" class="btn btn-secondary">Cancelar</a></td>
				<td><input type="submit" value="Guardar" class="btn btn-primary"/></td>
			</tr>
		</table>
	</mvc:form>
</body>
</html>