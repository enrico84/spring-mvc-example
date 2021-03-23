<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista di Player</title>
</head>
<body>
	<h1>Lista di Player</h1>
	<table border="2" width="70%" cellpadding="2">
		<tr><th>Id</th><th>Nome</th><th>Cognome</th><th>Squadra</th><th>Modifica</th><th>Elimina</th></tr>  
	   <c:forEach var="p" items="${players}">   
		   <tr>  
		   <td>${p.id}</td>  
		   <td>${p.name}</td>  
		   <td>${p.lastName}</td>  
		   <td>${p.team}</td>  
		   <td><a href="editplayer/${p.id}">Edit</a></td>  
		   <td><a href="deleteplayer/${p.id}">Delete</a></td>  
		   </tr>  
	   </c:forEach>  
	</table>  
	<br/>  
	<a href="addform">Aggiungi nuovo Player</a>  
</body>
</html>