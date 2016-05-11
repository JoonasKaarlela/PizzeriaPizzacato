<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Yhteystiedot</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
</head>
<body>

	<div id="wrapper">
	
		<div id="header">
			<div id="logo">
				<img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato
			</div>

			<div id="kirjaudu">
				<c:choose>

					<c:when test="${sessionScope.kayttaja != null}">
						<p>
							<strong> <i class="fa fa-user"></i> ${sessionScope.kayttaja.getKayttajatunnus()} </strong>
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
								<div style="float:left; margin: 0 5px;">
									<button type=submit>kirjaudu</button>
								</div>
								<div style="float:left;">
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
						<b>Yhteystiedot</b><br>Kuusitie 6<br> 00270 
						Helsinki <br> 040 123 456
					</p>
				</div>
				<div id="aukioloajat">
					<p>
						<b>Aukioloajat</b><br> Ma-To 11:00 - 21:00<br> Pe-La 11:00 - 22:00<br> Su 12:00 - 19:00
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
			<img src="banner-yhttiedot.jpg">
		</div>

		<!-- YHTEYSTIEDOT -->
		<div id="ytiedot">
			<p>
				<b>Yhteystiedot</b><br> Kuusitie 6<br> 00270
				Helsinki <br> 040 123 456<br>
				<br> <b>Aukioloajat</b><br> Ma-To 11:00 - 21:00<br>
				Pe-La 11:00 - 22:00<br> Su 12:00 - 19:00
			</p>
		</div>
		<div class="clear"></div>
		<!-- KARTTA -->
		<div id="map">
			<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1983.1470562071836!2d24.897437716107422!3d60.194849181970014!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x469209f621ac181f%3A0xcf44d83f44483b94!2sKuusitie+6%2C+00270+Helsinki%2C+Suomi!5e0!3m2!1sfi!2sus!4v1461924295426"
			 width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
		</div>

	</div>
	<!--  WRAPPER LOPPUU -->


</body>
</html>
