<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tilaukset | ${sessionScope.kayttaja.getKayttajatunnus()}
</title>
</head>
<body>


	<c:forEach items="${tilaukset}" var="tilaus">
			${tilaus.getTilausId()}
		</c:forEach>

</body>
</html>