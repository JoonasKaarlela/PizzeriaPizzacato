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
					
						<c:when test="${kayttaja != null}">
							<h1> Hei, <c:out value="${kayttaja.getKayttajatunnus()}"></c:out> </h1>
							<div> <a href="logout"> kirjaudu ulos </a> </div>
						</c:when>
						
						<c:otherwise>
						<form method=post action="Kirjaudu" id="kirjaudu_form">
							<div id="kayttajatunnus">
								<div> <input placeholder=kayttajatunnus name=kayttajatunnus /> </div>
							</div>
							<div id="salasana"> 
								<div> <input placeholder=salasana name=salasana type=password /> </div>
							</div>
							<div id="submit"> 
								<div> <button type=submit> kirjaudu </button> </div>
							</div> 
						</form>
						</c:otherwise>
						
					</c:choose>
			</div>

			<!--  NAVIGOINTI -->
            <div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="#">Etusivu</a></li>
					<li><a href="#">Menu</a></li>
					<li><a href="#">Yhteystiedot</a></li>
				</ul>
			</div>
		</div>
		
        <div class="clear"></div>
        
        <!--  BANNERI -->
		<div id="banner">
			<div class="bannertxt">Pizzaa</div>
		</div>
        
        <!--  PIZZA MENU ALKAA -->
		<div id="content">
			<div class="menu">
				
                <div class="menuitemwrap">
                    <div class="menuitem">

                        <!--  LOOPPAA PIZZAT -->
                        <c:forEach items="${pizzat}" var="pizza">

                        	<!--  JOS PIZZA LISTALLA=TRUE, NÄYTÄ PIZZA -->
                        <c:choose>
                        <c:when test="${pizza.getListalla()}">
	                    	<form method=post action=lisaaPizza>
		                        <table>
		                       
		                        		<tr>
		                                	<td valign="middle"><div class="pizzakuva"><img src="${pizza.getKuva()}" width="120" /></div></td>
		                               		<td>
		                                		<div class="pizzainfo">
			                                        <div class="pizzanimi"> ${pizza.getNimi()} </div>
			                                    	<div class="pizzakuvaus"> ${pizza.getKuvaus()}</div>                                     
													<div class="pizzahinta"> ${pizza.getHinta()}€ <button type=submit value="Lisää">Lisää</button></div>
		                                    	</div>
											<input type=hidden name=pizzan_id value="${pizza.getPizza_id()}" />
		                                	</td>
		                       		    </tr>
		                        
		                        </table>
	                        </form>
                        </c:when>
                        </c:choose>
                                        	
                        </c:forEach>
                    </div>
                </div>
                
				<div class="clear"></div>
				
				
			</div>
		</div>
		
	</div>

</body>
</html>
