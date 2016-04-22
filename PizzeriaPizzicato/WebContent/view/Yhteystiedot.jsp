<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Menu</title>
<link href="styles.css" rel="stylesheet" type="text/css">
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
					
					<div class="clear"></div>
					<div id="yhteystiedot">
					<p><b>Yhteystiedot</b><br>
					Pasilanraitio 10 D92<br>
					00240 Helsinki <br>
					040 840 0987</p>
					</div>
					<div id="aukioloajat">
					<p><b>Aukioloajat</b><br>
					Ma-To 09:00 - 22:30<br>
					Pe 09:00 - 02:00<br>
					La 09:00 - 02:00<br>
					Su 10:00 - 22:30
					</p>
					</div>
			</div>

			<!--  NAVIGOINTI -->
            <div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Yhteystiedot">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a></li>
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
        <div class="clear"></div>
        
        <!--  BANNERI -->
		<div id="banner">
			<div class="bannertxt">Yhteystiedot</div>
		</div>
		
		<!-- YHTEYSTIEDOT -->
        <div id="ytiedot">
        <p><b>Yhteystiedot</b><br>
		Pasilanraitio 10 D92<br>
		00240 Helsinki <br>
		040 840 0987<br><br>
		
		<b>Aukioloajat</b><br>
		Ma-To 09:00 - 22:30<br>
		Pe 09:00 - 02:00<br>
		La 09:00 - 02:00<br>
		Su 10:00 - 22:30</p>
        </div>
        <div class="clear"></div>
        <!-- KARTTA -->
        <div id="map">
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d1982.8179552658996!2d24.92329671623021!3d60.20029554792549!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4692098d73352fcd%3A0x85ff6f3804dbe019!2sPasilanraitio+10%2C+00101+Helsinki!5e0!3m2!1sfi!2sfi!4v1461155879337" width="600" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>
		
	</div><!--  WRAPPER LOPPUU -->


</body>
</html>
