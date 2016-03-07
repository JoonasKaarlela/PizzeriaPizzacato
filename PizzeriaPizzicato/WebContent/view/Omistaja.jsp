<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> Omistaja </title>
</head>
<body>

	<h1> Tervetuloa, <c:out value="${kayttaja.getKayttajatunnus()}"></c:out></h1>
	<a href="logout"> kirjaudu ulos </a>

</body>
</html>