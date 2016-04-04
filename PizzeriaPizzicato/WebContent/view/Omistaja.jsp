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
			                                		<input class="pizza_input" type="text" value="${pizza.getNimi()}" name="nimi" disabled required pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä"/> 
			                                	</div>
			                                    <div class="pizzakuvaus" >
			                                    	<textarea disabled class="pizza_input" name="kuvaus" required>${pizza.getKuvaus()}</textarea>
			                                    </div>                                     
												<div class="pizzahinta">
													<input type="text" class="pizza_input" value="${pizza.getHinta()}" name="hinta" disabled required pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx"/>
												</div>
												<div class="pizzantayte">
													<textarea disabled class="pizza_taytteet" name="taytteet" required> 
														<c:forEach items="${pizza.getTaytteet()}" var="tayte">
															 ${tayte.getNimi()}
														</c:forEach>
													 </textarea>
												</div>
												<!--  view tila napit -->
												<div class="muokkaa_con">
													<button type="button" class="muokkaa" value="muokkaa" onClick="muokkaa('${pizza.getPizza_id()}', '${current.index}'); return false;">Muokkaa</button>
												</div>	
												<!--  muokkaus tila napit -->
												<div class="muokkaus hidden">
													<button type="submit" value="tallenna" class="tallenna"> Tallenna </button>
                                                	<button type="button" value="poista" class='poista'>Poista</button>
                                                	<button type="button" value="peruuta" class='peruuta'> Peruuta </button>
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
			
			<!--OMISTJA PANEELI! -->
			<div id="apanel">
		        <div id="apanelwrapper">
		            <div id="apanelcontent">
		                <h1>Omistajapaneeli</h1>
		                
		                <button>Lisää ja muokkaa täytteitä</button>
		                
		                <h2>Lisää pizza</h2>
		                
		                <!-- LISÄÄ PIZZA -->
		                <div>
		                    <form method="post" action="lisaa">
		                        <label>Pizzannimi</label><input name="nimi" placeholder="pizzan nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" />
		                        <label>Pizzan kuvaus</label><input name="kuvaus" placeholder="kuvaus" />
		                        <label>Pizzan hinta</label><input name="hinta" placeholder="hinta" pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx" />
		                        <label>Täyte1</label><select><option>Salami</option><option>kinkku</option></select>
		                        <br />
		                        <button type="submit"> Lisää Pizza </button>
		                    </form>
		                </div>
		                
		            </div>
		        </div>
			</div>
			
			
		</div><!-- CONTENT LOPPUU -->
		
	</div>

</body>
</html>
