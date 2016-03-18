<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Omistaja </title>
<link href="styles.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript" src="omistaja.js"></script>
</head>
<body>


<div id="wrapper">
		
		<div id="header">
			<div id="logo"><img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato</div>
			
			<!-- KIRJAUDU -->
			<div id="kirjaudu">
				<h1> Tervetuloa, <c:out value="${sessionScope.kayttaja.getKayttajatunnus()}"></c:out></h1>
				<a href="kirjauduUlos"> kirjaudu ulos </a>
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
                        <c:forEach items="${pizzat}" var="pizza" varStatus="current">

                        	<!--  JOS PIZZA LISTALLA=TRUE, NÄYTÄ PIZZA -->
                        <div class="menuitemwrap">
                   			<div class="menuitem">
	                    	
	                    	<form method=post action=muokkaa>
		                        <table id="pizzat">
		                       
		                        	<tr class='pizza'>
		                                <td valign="middle"><div class="pizzakuva"><img src="${pizza.getKuva()}" style="width:150px;height:125px;" /></div></td>
		                               	<td valign="top">
		                                	<div class="pizzainfo">
			                                	<div class="pizzanimi">
			                                		<input class="pizza_input" type="text" value="${pizza.getNimi()}" name="nimi" disabled required pattern="^\s*([0-9a-zA-Z]+)\s*$"/> 
			                                	</div>
			                                    <div class="pizzakuvaus" >
			                                    	<textarea disabled class="pizza_input" name="kuvaus" required pattern="^\s*([0-9a-zA-Z]+)\s*$">${pizza.getKuvaus()}</textarea>
			                                    </div>                                     
												<div class="pizzahinta">
													<input type="text" class="pizza_input" value="${pizza.getHinta()}" name="hinta" disabled required pattern="[0-9]{5}"/>
												</div>
												<div class="pizzantayte">
													<textarea disabled class="pizza_taytteet" name="taytteet" required pattern="^\s*([0-9a-zA-Z]+)\s*$"> ${pizza.getTaytteet()} </textarea>
												</div>
												<!--  view tila napit -->
												<div class="muokkaa_con">
													<button type="button" class="muokkaa" value="muokkaa" onClick="muokkaa('${pizza.getPizza_id()}', '${current.index}'); return false;">Muokkaa</button>
												</div>	
												<!--  muokkaus tila napit -->
												<div class="muokkaus hidden">
													<button type="submit" value="tallenna" class="tallenna"> Tallenna </button>
                                                	<button type="button" value="poista" class='poista'>Poista</button>
                                                </div>
                                                <div class="listalla hidden">
                                                	<label for="piilossa"> listalla </label>
                                                	<input type="checkbox" value="piilota" name="piilossa" id="piilossa" />
                                                </div>
		                                    </div>
											<input type=hidden name=id value="${pizza.getPizza_id()}" class="hidden" />
		                                </td>
		                        	</tr>
		                        
		                        </table>
	                        </form>
                        
                        	</div><!-- MENUITEM LOPPUU -->
                		</div><!-- MENUITEMWRAP LOPPUU -->       
                                        	
                        </c:forEach>

                
				<div class="clear"></div>
				
				
			</div><!-- MENU LOPPUU -->
			
			<!-- LISÄÄ PIZZA -->
			<div id="uusiPizza">
				<form method=post action=lisaa>
					<input name="nimi" placeholder="pizzan nimi" />
					<input name="kuvaus" placeholder="kuvaus" />
					<input name="hinta" placeholder="hinta" />
					<textarea name="taytteet" placeholder="taytteet"></textarea>
					<button type=submit> lisaa </button>
				</form>
			</div>
			
		</div><!-- CONTENT LOPPUU -->
		
	</div>

</body>
</html>
