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

				<!-- KIRJAUDU || KÄYTTÄJÄNIMI -->
				<c:choose>

					<c:when test="${sessionScope.kayttaja != null}">
						<h3>${sessionScope.kayttaja.getKayttajatunnus()}</h3>
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
									<input placeholder=salasana name=salasana type=password required />
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
			</div>

			<!--  NAVIGOINTI -->
			<div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Yhteystiedot">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a></li>
				</ul>
			</div>
			<!-- NAV LOPPUU -->
		</div>

		<div class="clear"></div>


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
									<th> lkm </th>
									<th> poista </th>
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
										  <td> x${item.value.size()} </td>
										  <td> <a href="poistaKorista?pizza_id=${item.key}"> <b style="color: crimson"> poista </b> </a> </td>
										</tr>
									</c:when>
								</c:choose>
							</c:forEach>
						</tbody>
						</table>

						<form action=suoritaTilaus method=post onSubmit="validoi()"
							id="ostoskori_lomake">
							<h2>Osoite tiedot</h2>
							<strong class="error" style="color: crimson;"></strong> <input
								name="kayttajatunnus_rek" maxlength="25" required
								placeholder="kayttajatunnus"
								value="${sessionScope.kayttaja.getKayttajatunnus()}" /> <input
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
					</div>


					<div id="ostoskori_tilaus">
						<c:if test="${requestScope.tyhja == null}">
							<strong style="font-size: 100px;"> ${requestScope.summa}
								€ </strong>
						</c:if>
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
