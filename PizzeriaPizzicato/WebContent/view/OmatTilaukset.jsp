<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Menu</title>
		<link href="styles.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<script type="text/javascript" src="notification.js"></script>
	</head>
<body>

	<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
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

				</ul>
			</div>
			<!-- NAV LOPPUU -->
		</div>

		<div class="clear"></div>

		<!--  BANNERI -->
		<div id="banner">
			<img src="banner-tilaukset.jpg">
		</div>

		<!--  PIZZA MENU ALKAA -->
		<div id="content">
			<c:choose>
			
				<c:when test="${!tilaukset.isEmpty() && tilaukset != null}">
					<table style="width:90%; height:auto; text-align:center; margin: 50px auto;">
				
				<thead>
					<tr style="height:75px;">
						<td>Nro</td>
						<td>ID</td>
						<td>Kayttajatunnus</td>
						<td>Tilausaika</td>
						<td>Hinta</td>
						<td>Tila</td>
						<td><!-- peruuta --></td>
					</tr>
				</thead>
			
				<tbody>
					<c:forEach items="${tilaukset}" var="tilaus" varStatus="current">
						<tr>
							<td>${current.index + 1}</td>
							<td>${tilaus.getTilaus_id()}</td>
							<td>${tilaus.getKayttaja().getKayttajatunnus()}</td>
							<td>${tilaus.getTilausaika()}</td>
							<td>${tilaus.getHinta()}</td>
							<td>${tilaus.getTila()}</td>
							<c:if test="${tilaus.voidaanPeruuttaa()}">
								<td><a href="PoistaTilaus?tilaus_id=${tilaus.getTilaus_id()}" style="color:crimson;"> Peruuta </a></td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<h3> Et ole tehnyt tilauksia. </h3>
		</c:otherwise>
			
		</c:choose>
			
			
		</div>
		<!-- CONTENT LOPPUU -->

	</div>


</body>
</html>
