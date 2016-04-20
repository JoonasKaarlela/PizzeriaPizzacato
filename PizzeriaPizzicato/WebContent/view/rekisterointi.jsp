<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<link href="rekisterointi.css" rel="stylesheet" type="text/css">
<script src="rekisterointi.js"></script>
<script src="notification.js"> </script>
<title> Rekisterointi </title>
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
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
        <div class="clear"></div>
        
        <!--  BANNERI -->
		<div id="banner">
			<div class="bannertxt">Pizzaa</div>
		</div>
		
		<div id="rekisterointi">
			<form action=Rekisterointi method=post onSubmit="validoi()" id="rekisterointi_form">
				<h2> Rekisteröidy </h2>
				<strong class="error" style="color:crimson;"></strong>
				<input name="kayttajatunnus_rek" maxlength="25" required placeholder="kayttajatunnus" />
				<input name="salasana_rek" type=password required  placeholder="salasana" />
				<input name="salasana2_rek" type=password  required  placeholder="salasana (uudestaan)"/>
				<input name="osoite_rek"  maxlength="50" required placeholder="osoite" />
				<input name="puh_rek" type="tel" maxlength="25" placeholder="puh" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" required />
				<input name="sahkoposti_rek" type="email" maxlength="30" required placeholder="sähköposti" />
				<button type=submit> submit </button>
			</form>
		</div>
	
	
	
	
	
	
	</div>
</body>
</html>