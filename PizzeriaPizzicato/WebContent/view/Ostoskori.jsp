<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="styles.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>

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
					<li><a href="#">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a></li>
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
        <div class="clear"></div>
        
        
        
        <div id="ostoskori">
        	<form method=post action=suoritaTilaus>
        		<div>
        			
        			<c:choose>
        			
        				<c:when test="${sessionScope.ostoskori != null && !sessionScope.ostoskori.isEmpty()}">
        				
        					<h1> Valitsemasi pizzat </h1>
        				
        					<ul>
        						<c:forEach items="${sessionScope.ostoskori}" var="pizza"> 
        							<li>
										<div> <strong> ${pizza.getNimi()} </strong> </div>
										<div> ${pizza.getHinta()} </div>
										<div>
											<c:forEach items="${pizza.getTaytteet()}" var="tayte">
												${tayte.getNimi()}
											</c:forEach>
										</div>
										<div>
											<a href="poistaKorista?pizza_id=${pizza.getPizza_id()}"> <b style="color:crimson"> poista </b> </a>
										</div>   					
        							</li>
        						</c:forEach>
        						<div>
        							<button type=submit> Tilaa </button>
        						</div>     
        					</ul>
        				</c:when>
        			
        				<c:otherwise>
        					<p> Ostorkorisi on tyhjä! :( </p>
        				</c:otherwise>
        			
        			</c:choose>
        		
        			
        		</div>
        	</form>
        </div>
       </div>

</body>
</html>