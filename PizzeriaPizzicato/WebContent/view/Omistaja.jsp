<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title> Omistaja </title>
<link href="styles.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="omistaja.js"></script>
<script type="text/javascript" src="notification.js"></script>
</head>
<body>


<div id="wrapper">

		<div class="notification">
			<c:if test="${sessionScope.notification != null}">
				<strong>${sessionScope.notification}</strong>
			</c:if>
		</div>

		<div id="header">
			<div id="logo"><img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato</div>
			
			<!-- KIRJAUDU -->
			<div id="kirjaudu">
				<h1> <c:out value="${sessionScope.kayttaja.getKayttajatunnus()}"></c:out></h1>
				<a href="kirjauduUlos"> kirjaudu ulos </a>
			</div>
			
			
			<!--  NAVIGOINTI -->
            <div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="Menu">Etusivu</a></li>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Menu">Yhteystiedot</a></li>
				</ul>
			</div><!-- NAV LOPPUU -->
		</div>
		
        <div class="clear"></div>
        
        <!--  BANNERI -->
		<div id="banner">
			<div class="bannertxt">Pizzaa</div>
		</div>
        
        <!--OMISTAJA PANEELI! -->
			<div id="apanel">
		        <div id="apanelwrapper">
		            <div id="apanelcontent">
		                <h1>Omistajapaneeli</h1>
		                
		             	<a href="taytteidenhallinta"> Muokkaa ja poista täytteitä  </a>
		                
		                <h2>Lisää pizza</h2>
		                
		                <!-- LISÄÄ PIZZA -->
		                <div>
		                    <form method="post" action="lisaa">
		                        <label>Pizzannimi</label><input name="nimi" placeholder="pizzan nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" />
		                        <label>Pizzan kuvaus</label><input name="kuvaus" placeholder="kuvaus" />
		                        <label>Pizzan hinta</label><input name="hinta" placeholder="hinta" pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx" />
		                        <select multiple name="taytteet">
		                        	<c:forEach items="${taytteet}" var="tayte" varStatus="current">
		                        		<label> Täyte ${current.index} </label><option value="${tayte.getTayte_id()}"> ${tayte.getNimi()} </option>
		                        	</c:forEach>
		                        </select>
		                        <br />
		                        <button type="submit"> Lisää Pizza </button>
		                    </form>
		                </div>
		                
		            </div>
		        </div>
			</div>
		<!-- OMISTAJA PANEELI LOPPUU -->
        
        <!--  PIZZA MENU ALKAA -->
		<div id="content">
			<div class="menu">
				
              <!-- PIZZAT -->
              <c:forEach items="${pizzat}" var="pizza" varStatus="current">
              	<div class="menuitemwrap">
                   <div class="menuitem">
	                 <form method=post action=muokkaa>
		               <table id="pizzat">
		                 <tr class='pizza'>
		                   <td valign="middle"><div class="pizzakuva"><img src="${pizza.getKuva()}" style="width:150px;height:125px;" /></div></td>
		                   <td valign="top">
		                     <div class="pizzainfo">
			                 	<div class="nakyma">
	                 				<div class="pizzanimi"> ${pizza.getNimi()} </div>
	                 				<div class="pizzakuvaus"> ${pizza.getKuvaus()} </div>
	                 				<div class="pizzahinta"> ${pizza.getHinta()} </div>
	                 				<div class="pizzatayte">
	                 					<c:forEach items="${pizza.getTaytteet()}" var="tayte" varStatus="status">
	                 						${tayte.getNimi()}
										</c:forEach>
	                 				</div>
	                 				<div class="pizzamuokkaa">
	                 					<button type="button" onClick="muokkaa('${current.index}'); return false;"> <b> muokkaa </b> </button>
	                 				</div>
	                 			</div>
	                 			<div class="muokkaus hidden">
	                 				<div class="pizzanimi">
	                 					<input class="pizza_input" type="text" value="${pizza.getNimi()}" name="nimi" required pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä"/> 
	                 				</div>
	                 				<div class="pizzakuvaus">
	                 					<textarea  class="pizza_input" name="kuvaus" required>${pizza.getKuvaus()}</textarea>
	                 				</div>
	                 				<div class="pizzahinta">
	                 					<input type="text" class="pizza_input" value="${pizza.getHinta()}" name="hinta" required pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx"/>
	                 				</div>
	                 				<div class="pizzatayte">
	                 					<select multiple name="taytteet">
											<c:forEach items="${taytteet}" var="tayte">
												<option value="${tayte.getTayte_id()}"> ${tayte.getNimi()}  </option>
											</c:forEach>
										</select>
	                 				</div>
	                 				<div class="pizzamuokkaa">
	                 					<button type="submit" value="tallenna" class="tallenna"> Tallenna </button>
                            			<a href="poista?id=${pizza.getPizza_id()}"> poista </a>
                            			<button type="button" value="peruuta" class='peruuta' onClick="muokkaa('${current.index}'); return false;"> Peruuta </button>
                            			<label for="piilossa"> listalla </label>
                           				<input type="checkbox" value="piilota" name="piilossa" id="piilossa" />
	                 				</div>
	                 			</div>
	                 		</div>
							<input type=hidden name=id value="${pizza.getPizza_id()}" class="hidden" />
		                  </td>
		                 </tr>      
		                </table>
	                  </form>
					</div>
               	</div>     
			</c:forEach>
			
			<div class="clear"></div>
				
			</div><!-- MENU LOPPUU -->
			
		</div><!-- CONTENT LOPPUU -->
		
	</div>
	
<script type="text/javascript" src="omistaja.js"></script>
</body>
</html>
