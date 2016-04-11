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
                   			<form action="lisaaKoriin" method="post">
		                        <table>
		                        	<tr>
		                                <td valign="middle"><div class="pizzakuva"><img src="${pizza.getKuva()}" style="width:150px;height:125px;" /></div></td>
		                               	<td valign="top">
		                                	<div class="pizzainfo">
			                                	<div class="pizzanimi"> ${pizza.getNimi()} </div>
			                                    <div class="pizzakuvaus"> ${pizza.getKuvaus()}</div>                                     
												<div class="pizzahinta"> ${pizza.getHinta()}€</div>
												<div class="pizzantayte">
													<c:forEach items="${pizza.getTaytteet()}" var="tayte" varStatus="status">
														${tayte.getNimi()}
													</c:forEach>
												</div>
												<div style="display:inline-block;">
													<button type=submit> <strong> valitse </strong> </button>
													<select name="maara">
														<option value=1> 1 </option>
														<option value=2> 2 </option>
														<option value=3> 3 </option>
														<option value=4> 4 </option>
														<option value=5> 5 </option>
													</select>
												</div>
		                                    </div>
											<input type=hidden name=pizza_id value="${pizza.getPizza_id()}" />
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
