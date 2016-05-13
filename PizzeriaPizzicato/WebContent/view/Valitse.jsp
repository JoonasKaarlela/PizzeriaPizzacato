<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Fantasia</title>
		<link href="styles.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="ValitsePizza.css" >
		<script type="text/javascript" src="notification.js"></script>
   </head>
<body>

	<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>
	
	<div class="error">
		<c:if test="${error != null}">
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
			<img src="banner.jpg">
		</div>

		
		<form id='container'>
			
			<div id='valitse_top'> 
				<h1> ${pizza.getNimi()} </h1>
			</div>
			
			<div id='valitse_left'>
				<div> <img src="${pizza.getKuva()}" /> </div>
			</div>
			
			<div  id='valitse_right'>
				
				<div>
					<h3> Kuvaus </h3>
					<div>
						${pizza.getKuvaus()}
					</div>
				</div>
			
			
				<div>
					<div>
						<h3>Täytteet</h3>
						<div>
							<c:forEach items="${pizza.getTaytteet()}" var="tayte"  varStatus="status">
								${ tayte.getNimi()}<c:if test="${status.index != pizza.getTaytteet().size() - 1 }">, </c:if>
							</c:forEach>
						</div>
					</div>

				</div>
			</div>
			
			<div>
				<div>
					<h3> Mausteet: </h3>
				</div>
				<div>
					<table>
						<c:forEach items="${mausteet}" var="mauste">
							<tr>
								<td> ${mauste.getNimi()} </td>
								<td> <input type='checkbox' name='mausteet' value='${mauste.getMauste_id() }' /> </td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		
			<div>
				<button type='submit'> valitse </button>
				<input type='number' min='1' max='10' value='1' name="maara" />
			</div>
		
		</form>
		
		
	</div>

	
	</body>
	</html>