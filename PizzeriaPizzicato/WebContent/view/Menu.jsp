<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList, Pizzacato.model.Pizza" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Menu</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<link href="grid.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<div id="wrapper">
		
		<div id="header">
			<div id="logo"><img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato</div>
			<div id="kirjaudu">
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
			</div>

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
        
		<div id="banner">
			<div class="bannertxt">Pizzaa</div>
		</div>
        
		<div id="content">
			<div class="menu">
				
                <div class="menuitemwrap">
                    <div class="menuitem">
                        <table>
                        
                        <%
                        	ArrayList<Pizza> pizzat = new ArrayList<>(); 
							pizzat = (ArrayList<Pizza>) request.getAttribute("pizzat");
                        	for(Pizza pizza : pizzat){
                        %>
       
                        <tr>
                        	<form method=post action=lisaaPizza>
                                <td valign="middle"><div class="pizzakuva"><img src=<%pizza.getKuva(); %> width="120" /></div></td>
                                <td>
                                	<div class="pizzainfo">
                                        <div class="pizzanimi"><%=pizza.getNimi() %></div>
                                    	<div class="pizzakuvaus"><%=pizza.getKuvaus() %></div>                                     
										<div class="pizzahinta">13,37 € <button type=submit value="Lisää">Lisää</button></div>
                                    </div>
									<input type=hidden name=pizzan_id value=<%=pizza.getPizza_id() %> />
                                </td>
                             </form>
                         </tr>
                        <% } %>
                        </table>
                    </div>
                </div>
                
                
                <div class="menuitemwrap">
                    <div class="menuitem">
                        <table>
                            <tr>
                                <td valign="middle"><div class="pizzakuva"><img src="pizza.png" width="120" /></div></td>
                                <td>
                                	<div class="pizzainfo">
		
                                        <div class="pizzanimi">Joku Pizza</div>

                                    	<div class="pizzakuvaus">Fusce purus morbi tortor magna condimentum vel, placerat id blandit sit amet tortor.</div>
                                        
										<div class="pizzahinta">13,37 € <button value="Lisää">Lisää</button></div>
                                    </div>

                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
                
				<div class="clear"></div>
				
				
			</div>
		</div>
		
	</div>

</body>
</html>