<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accesso non consentito</title>
</head>
<body>

	<h3> Accesso negato.</h3>
	${msg}
	<br>
	<a href="${pageContext.request.contextPath}/dashboard">Indietro</a>

</body>
</html>