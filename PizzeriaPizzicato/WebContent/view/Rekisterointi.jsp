<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Menu</title>
		<link href="styles.css" rel="stylesheet" type="text/css">
		<link href="rekisterointi.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="notification.js"></script>
		<script type="text/javascript" src="rekisterointi.js"></script>
		
	</head>
<body>

		<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>
	
	<div class="error">
          <b> ${error} </b>
    </div>


	<div id="wrapper">

		<div id="header">
			<div id="logo">
				<img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato
			</div>

			<div id="kirjaudu">
				<c:choose>

					<c:when test="${sessionScope.kayttaja != null}">
						<p>
							${sessionScope.kayttaja.getKayttajatunnus()}
						</p>
						<div>
							<a href="kirjauduUlos"> kirjaudu ulos </a>
						</div>
					</c:when>
					

					<c:otherwise>
						<form method=post action="Kirjaudu" id="kirjaudu_form">
							<div id="kayttajatunnus">
								<div>
									<input placeholder=kayttajatunnus name=kayttajatunnus required />
								</div>
							</div>
							<div id="salasana">
								<div>
									<input placeholder=salasana name=salasana type=password
										required />
								</div>
							</div>
							<div id="submit" style="display: inline-block;">
								<div>
									<button type=submit>kirjaudu</button>
								</div>
								<div>
									<a href="Rekisterointi"> rekisteroidy </a>
								</div>
							</div>
						</form>
						<div class="error">
							<p style="color: crimson">
								<c:out value="${error}"></c:out>
							</p>
						</div>
					</c:otherwise>

				</c:choose>
				
				<div id="yhteystiedot">
					<p>
						<b>Yhteystiedot</b><br> Pasilanraitio 10 D92<br> 00240
						Helsinki <br> 040 840 0987
					</p>
				</div>
				<div id="aukioloajat">
					<p>
						<b>Aukioloajat</b><br> Ma-To 09:00 - 22:30<br> Pe 09:00
						- 02:00<br> La 09:00 - 02:00<br> Su 10:00 - 22:30
					</p>
				</div>
				
			</div>

			<!--  NAVIGOINTI -->
			<div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Yhteystiedot">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a> </li>

					<c:if test="${sessionScope.kayttaja != null}">
						<li><a href="Tilaukset">Tilaukset</a></li>
					</c:if>
					
					<c:if test="${sessionScope.kayttaja.isOmistaja()}">
						<li><a href="PizzojenHallinta"> Hallinnoi </a> </li>
					</c:if>

					<c:if test="${sessionScope.kayttaja.isKuljettaja()}">
						<li><a href="Kuljettaja"> Kuljetukset </a></li>
					</c:if>
					
					<c:if test="${sessionScope.kayttaja.isKokki()}">
						<li><a href="Kokki"> Odottavat </a> </li>
					</c:if>

				</ul>
			</div>
			<!-- NAV LOPPUU -->
		</div>

		<div class="clear"></div>

		<!--  BANNERI -->
		<div id="banner">
			<img src="banner-rekisterointi">
		</div>
		
		
		<div id="rekisterointi">
		
			<form id="rekisterointi_form" action="Rekisterointi" method="POST">
				<label for=kayttajatunnus_rek> kayttaja </label>
				<input name="kayttajatunnus_rek" placeholder="kayttajatunnus" pattern="^[a-zA-Z][a-zA-Z0-9-_\.]{1,20}$" size="15"/>
				<label for=salasana_rek> salasana </label>
				<input name="salasana_rek" placeholder="(vähintään 8 merkkiä)" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" size="20" />
				<label for=salasana_rek2> salasana(uudestaan) </label>
				<input name="salasana2_rek" placeholder="(vähintään 8 merkkiä)" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" size="20" />
				<label for=osoite_rek> osoite </label>
				<input name="osoite_rek" placeholder="asiakastie123" size="20"/>
				<label for=puh> puhelin </label>
				<input name="puh" placeholder="0400123123" pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$" size="20" />
				<label for=sahkoposti> sahkoposti </label>
				<input name="sahkoposti" placeholder="asiakas@domain.fi" size="20" />
				<button type="submit"> Tallenna </button>
			</form>
		
		</div>
		
</div>
</body>
</html>