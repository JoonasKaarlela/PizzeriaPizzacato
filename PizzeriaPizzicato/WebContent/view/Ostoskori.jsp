<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="notification.js"></script>
<script type="text/javascript" src="rekisterointi.js"></script>
<link href="ostoskori.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<title>Ostoskori</title>
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

				</ul>
			</div>
			<!-- NAV LOPPUU -->
		</div>

		<div class="clear"></div>

		<!--  BANNERI -->
		<div id="banner">
			<div class="bannertxt">Ostoskori</div>
		</div>
		
		
		<div id="ostoskori">
			<!--  PIZZAT || ILMOITUS -->
			<c:choose>
				<c:when test="${requestScope.tyhja == null}">

					<h1>Valitsemasi pizzat</h1>

					<div id="ostoskori_middle">
						<table id="ostoskori_pizzat">
						
							<thead>
								<tr>
									<th> Kuva </th>
									<th> Nimi </th>
									<th> Hinta</th>
									<th> Taytteet </th>
									<th> Mausteet </th>
									<th> lkm </th>
								</tr>
							</thead>
							
							<tbody>
							<c:forEach items="${sessionScope.ostoskori}" var="item">
								<c:choose>
									<c:when test="${item.key != null || item.value != null || !item.value.isEmpty()}">
										<tr class="ostoskori_pizza">
										  <td> <img style="width:50px; height:50px; object-fit:fill;" src="${item.value.get(0).getKuva()}" /> </td>
										  <td> ${item.value.get(0).getNimi()} </td>
										  <td> ${item.value.get(0).getHinta()}€ </td>
										  <td>
										  	 <c:forEach items="${item.value.get(0).getTaytteet()}" var="tayte">
										    	${tayte.getNimi()}
										     </c:forEach>
										  </td>
										  <td>
										  	 <c:forEach items="${item.value.get(0).getMausteet()}" var="mauste">
										  	 	${mauste.getNimi()}
										  	 </c:forEach>
										  </td>
										  <td> x${item.value.size()} </td>
										  <td> <a href="poistaKorista?pizza_id=${item.key}"> <b style="color: crimson"> poista </b> </a> </td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>
						</tbody>
						</table>
					</div>

					<div id="ostoskori_bottom">
						<form action=suoritaTilaus method=post onSubmit="validoi()"
							id="ostoskori_lomake">
							<h2>Osoite tiedot</h2>
							<strong class="error" style="color: crimson;"></strong> 
								<input
								name="osoite_rek" maxlength="50" required placeholder="osoite"
								value="${sessionScope.kayttaja.getOsoite()}" /> <input
								name="puh_rek" type="tel" maxlength="25" placeholder="puh"
								pattern="^(?:0|\(?\+33\)?\s?|0033\s?)[1-79](?:[\.\-\s]?\d\d){4}$"
								required value="${sessionScope.kayttaja.getPuh()}" /> <input
								name="sahkoposti_rek" type="email" maxlength="30" required
								placeholder="sähköposti"
								value="${sessionScope.kayttaja.getSahkoposti()}" />
							<c:if test="${!sessionScope.ostoskori.isEmpty()}">
								<button type=submit id="ostoskori_tilaus_button">Tilaa
								</button>
							</c:if>
						</form>

						<div id="ostoskori_tilaus">
							<c:if test="${requestScope.tyhja == null}">
								<div> <strong style="font-size: 100px;"> ${requestScope.summa}€ </strong>  </div>
							</c:if>
						</div>
					</div>
				</c:when>
					
				<c:otherwise>
					<div>
						<h3>Et ole valinnut tuotteita.</h3>
					</div>
				</c:otherwise>

			</c:choose>
		</div>
	</div>

</body>
</html>
