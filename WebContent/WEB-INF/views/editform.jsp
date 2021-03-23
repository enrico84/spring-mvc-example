<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Edit Player</title>
</head>
<body>
	<h1>Player selezionato</h1>
	<form:form method="POST" action="/spring-mvc-example/editsave">
		<table>
		<tr>
			<td></td>
			<td><form:hidden path="id"/> </td>
		</tr>
		<tr>
			<td>Nome: </td>
			<td><form:input path="name"  /> </td>
		</tr>
		<tr>
			<td>Cognome: </td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td>Squadra: </td>
			<td><form:input path="team" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="Salva Modifiche"></td>
		</tr>
		</table>
	</form:form>
</body>
</html>