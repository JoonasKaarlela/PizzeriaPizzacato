<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Valitse</title>
</head>
<body>
	
	<div id="wrapper">
		<div id="header">
			<div id="logo"><img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato</div>
			
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
							<div id="submit"> 
								<div> <button type=submit> kirjaudu </button> </div>
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
					<li><a href="Menu">Etusivu</a></li>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Menu">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a></li>
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
		
		<div id="valitse_pizza_container">
			
			<div id="pizza_vasen">
				<div> <h1> ${pizza.getNimi()} </h1>  </div>
				<div>
					<img id="pizza_kuva" src="${pizza.getKuva()}" />
				</div>
			</div>
			
			<form id="pizza_oikea" method=POST action="lisaaKoriin">
				<div>
					<div>
						<c:forEach items="${pizza.getTaytteet()}" var="tayte">
							<strong> ${tayte.getNimi()} </strong>
						</c:forEach>
					</div>
					<div>
						<div> <p> lisää </p> </div>
						<select multiple name="taytteet">
							<c:forEach items="${taytteet}" var="tayte">
								<option value="${tayte.getTayte_id()}"> ${tayte.getNimi()} </option>
							</c:forEach>
						</select>
						<input type="number" min="1" max="10" name="maara" />
					</div>
				</div>
				<div>
					<button type="submit"> lisää koriin </button>
				</div>
				<input type="hidden" name="pizza_id" value="${pizza.getPizza_id()}" />
			</form>
			
		</div>
		
	</div>
	
</body>
</html>