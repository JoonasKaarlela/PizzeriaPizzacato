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
	
	<div class="error">
          <b> ${error } </b>
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
								<div style="float:left; margin: 0 5px;">
									<button type=submit>kirjaudu</button>
								</div>
								<div style="float:left;">
									<a href="Rekisterointi"> rekisteroidy </a>
								</div>
							</div>
						</form>
					</c:otherwise>

				</c:choose>
				
				<div id="yhteystiedot">
					<p>
						<b>Yhteystiedot</b><br> Kuusitie 6<br> 00270
						Helsinki <br> 040 123 456
					</p>
				</div>
				<div id="aukioloajat">
					<p>
						<b>Aukioloajat</b><br> Ma-To 11:00 - 21:00<br> Pe-La 11:00
						- 22:00<br> Su 12:00 - 19:00
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
			<img src="banner-menu.jpg">
		</div>

		<!--  PIZZA MENU ALKAA -->
		<div id="content">
			<div class="menu">
				<!-- PIZZAT -->
				<c:forEach items="${pizzat}" var="pizza">
					<c:choose>
						<c:when test="${pizza.getListalla()}">
							<div class="menuitemwrap">
								<div class="menuitem">
										<table>
											<tr>
												<td valign="middle"><div class="pizzakuva">
														<img src="${pizza.getKuva()}"
															style="width: 150px; height: 125px;" />
													</div></td>
												<td valign="middle">
													<div class="pizzainfo">
														<div class="pizzanimi"> <h2> ${pizza.getNimi()} </h2> </div>
														<div class="pizzakuvaus">  ${pizza.getKuvaus()} </div>
														<div class="pizzahinta"> ${pizza.getHinta()}€ </div>
														<div class="pizzantayte">
															<p>
																<c:forEach items="${pizza.getTaytteet()}" var="tayte" varStatus="status">
																	${tayte.getNimi()}<c:if test="${status.index != pizza.getTaytteet().size()-1}">, </c:if>
																</c:forEach>
															</p>
														</div>
														<div style="display:'inline-block'">
															<a href="ValitsePizza?id=${pizza.getPizza_id() }"> valitse </a>
														</div>
															
														</div>
												</td>
											</tr>
										</table>
								</div>
								<!-- MENUITEM LOPPUU -->
							</div>
							<!-- MENUITEMWRAP LOPPUU -->
						</c:when>
					</c:choose>
				</c:forEach>


				<div class="clear"></div>

			</div>
			<!-- MENU LOPPUU -->
		</div>
		<!-- CONTENT LOPPUU -->

	</div>


</body>
</html>
