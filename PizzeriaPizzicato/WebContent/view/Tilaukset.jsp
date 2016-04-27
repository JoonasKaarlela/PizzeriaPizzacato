<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="notification.js"></script>
<script type="text/javascript" src="rekisterointi.js"></script>
<link href="tilaukset.css" rel="stylesheet" type="text/css">
<title>Tilaukset</title>
</head>
<body>

<div class="notification">
	<c:if test="${requestScope.notification != null}">
		<strong>${requestScope.notification}</strong>
	</c:if>
</div>


	<div id="wrapper">
	
		<div id="header">
			<div id="logo"><img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato</div>
			
			<!--  JOS KIRJAUTUNUT SISÄÄN NÄYTÄ KIRAJUDU ULOS, JOS EI NIIN NÄYTÄ KIRJAUTUMIS LOMAKE -->
			<div id="kirjaudu">
					<c:choose>
					
						<c:when test="${sessionScope.kayttaja != null}">
							<h1> <c:out value="${sessionScope.kayttaja.getKayttajatunnus()}"></c:out> </h1>
							<div> <a href="kirjauduUlos"> kirjaudu ulos </a> </div>
						</c:when>
						
						<c:otherwise>
						<form method=post action="Kirjaudu" id="kirjaudu_form" >
							<div id="kayttajatunnus">
								<div> <input placeholder=kayttajatunnus name=kayttajatunnus required /> </div>
							</div>
							<div id="salasana"> 
								<div> <input placeholder=salasana name=salasana type=password required /> </div>
							</div>
							<div id="submit" style="display:inline-block"> 
								<div> <button type=submit> kirjaudu </button> </div>
								<div> <a href="Rekisterointi"> rekisteroidy </a> </div>
							</div> 
						</form>
						<div class="error">
							<p style="color:crimson"> <c:out value="${error}"></c:out></p>
						</div>
						</c:otherwise>
						
					</c:choose>
			</div>

			<!--  NAVIGOINTI -->
            <div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Menu">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a></li>
					<li><a href="Tilaukset">Tilaukset</a></li>
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
		
		<div id="tilaukset">
			<table id="tilaukset_poyta">
				<thead>
					<tr>
						<th> Tilaustunnus </th>
						<th> Asiakas </th>
						<th> Tilausaika </th>
						<th> Hinta </th>
						<th> Tila </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tilaukset}" var="tilaus">
						<tr class="tilaus">
							<td>${tilaus.getTilaus_id()}</td>
							<td>${tilaus.getKayttaja().getKayttajatunnus()}</td>
							<td>${tilaus.getTilausaika()}</td>
							<td>${tilaus.getHinta()}</td>
							<td>${tilaus.getTila()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>

</body>
</html>
