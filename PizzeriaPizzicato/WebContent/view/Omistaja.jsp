<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Omistaja </title>
<link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="wrapper">
		
		<div id="header">
			<div id="logo"><img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato</div>
			
			<!-- KIRJAUDU -->
			<div id="kirjaudu">
				<h1> Tervetuloa, <c:out value="${kayttaja.getKayttajatunnus()}"></c:out></h1>
				<a href="logout"> kirjaudu ulos </a>
			</div>
			
			
			<!--  NAVIGOINTI -->
            <div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="#">Etusivu</a></li>
					<li><a href="#">Menu</a></li>
					<li><a href="#">Yhteystiedot</a></li>
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
        <div class="clear"></div>
        
        <!--  BANNERI -->
		<div id="banner">
			<div class="bannertxt">Pizzaa</div>
		</div>
        
        <!--  PIZZA MENU ALKAA -->
		<div id="content">
			<div class="menu">
				
                

                        <!--  LOOPPAA PIZZAT -->
                        <c:forEach items="${pizzat}" var="pizza">

                        	<!--  JOS PIZZA LISTALLA=TRUE, NÄYTÄ PIZZA -->
                        <c:choose>
                        <c:when test="${pizza.getListalla()}">
                        
                        <div class="menuitemwrap">
                   			<div class="menuitem">
	                    	
	                    	<form method=post action=lisaaPizza>
		                        <table>
		                       
		                        	<tr>
		                                <td valign="middle"><div class="pizzakuva"><img src="${pizza.getKuva()}" /></div></td>
		                               	<td valign="top">
		                                	<div class="pizzainfo">
			                                	<div class="pizzanimi"><input type="text" value="${pizza.getNimi()}" disabled /> </div>
			                                    <div class="pizzakuvaus"><textarea disabled>${pizza.getKuvaus()}</textarea></div>                                     
												<div class="pizzahinta"><input type="text" value="${pizza.getHinta()}€" disabled /></div>
												<button type="button" value="muokkaa">Muokkaa</button>
                                                <button type="button" value="poista">Poista</button>
                                                <input type="checkbox" value="piilota"/>Piilota Pizza
		                                    </div>
											<input type=hidden name=pizzan_id value="${pizza.getPizza_id()}" />
		                                </td>
		                        	</tr>
		                        
		                        </table>
	                        </form>
                        
                        	</div><!-- MENUITEM LOPPUU -->
                		</div><!-- MENUITEMWRAP LOPPUU -->
                        
                        </c:when>
                        </c:choose>
                                        	
                        </c:forEach>

                
				<div class="clear"></div>
				
				
			</div><!-- MENU LOPPUU -->
		</div><!-- CONTENT LOPPUU -->
		
	</div>

</body>
</html>