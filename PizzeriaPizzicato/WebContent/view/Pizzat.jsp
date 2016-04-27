<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hallintasivu - Pizzat</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script type="text/javascript" src="taytteet.js"></script>

</head>
<body>

<div id="wrapper">	

    <h1>Hallintasivu</h1>
    <h3><a href="Menu">Palaa pääsivulle</a></h3>
	<div id="hallintanav">
    	<ul>
        	<li>
            	<a href="Taytteet">Täytteet</a>
        	</li>
            <li class="aktiivinen">
            	<a href="Pizzat">Pizzat</a>
            </li>
            <li>	
            	<a href="#">Tilaukset</a>
            </li>
    	</ul>
    </div>
    <div class="clear"></div>
    <div id="taytelista">
        <table>
        	<tr>
            	<td colspan="5">
                <h2>Lisää pizza</h2>
		                
		                <!-- LISÄÄ PIZZA -->
		                <div>
		                    <form method="post" action="lisaa">
		                        <label>Pizzannimi</label><input name="nimi" placeholder="pizzan nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" /><br />
		                        <label>Pizzan kuvaus</label><input name="kuvaus" placeholder="kuvaus" /><br />
		                        <label>Pizzan hinta</label><input name="hinta" placeholder="hinta" pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx" /><br />
		                        <label>Pizzan täytteet</label><select multiple name="taytteet">
		                        	<c:forEach items="${taytteet}" var="tayte" varStatus="current">
		                        		<label> Täyte ${current.index} </label><option value="${tayte.getTayte_id()}"> ${tayte.getNimi()} </option>
		                        	</c:forEach>
		                        </select>
		                        <button type="submit"> Lisää Pizza </button>
		                    </form>
		                </div>
                
                
                
                </td>
            </tr>
            <tr style="border-top:1px solid #CCC;">
            	<td>
                	Nimi
                </td>
                <td>
                	Kuvaus
                </td>
                <td>
                	Hinta
                </td>
                <td>
                	Listalla
                </td>
                <td>
                </td>
           	</tr>
           	<c:forEach items="${pizzat}" var="pizza" varStatus="current">
            <tr>
            	<td colspan="5">
                    <form class="pizzaform" method="post" action="">
                    	<table>
                        <tr>
                        	<td>
                            	<input type="hidden" value="${pizza.getPizza_id()}" name="pizza_id">
                            	<input disabled type="text" value="${pizza.getNimi()}" name="nimi">
                            </td>
                            <td>
                            	<input disabled type="text" value="${pizza.getKuvaus()}" name="kuvaus">
                            </td>
                            <td>
                            	<input disabled type="text" value="${pizza.getHinta()}" name="hinta">
                            </td>
                            <td>
                            	<input disabled type="text" value="${pizza.getListalla()}" name="listalla">
                            </td>
                            <td rowspan="2" align="right">
                            	<button type="submit" value="muokkaa">Muokkaa</button><button value="poista">Poista</button>
                            </td>
                        </tr>
                        <tr>
                        	<td colspan="5">
                        		Täyte 1
                        		<select>
                        			<option value="" selected>Valitse täyte</option>
                        			<c:forEach items="${pizza.getTaytteet()}" var="tayte" varStatus="status">
                        			<option>${tayte.getNimi()}</option>
                        			</c:forEach>
                        		</select>
                        	</td>
                        </tr>
                        </table>
                    </form>
                </td>
            </tr>
            </c:forEach>
    	</table>
    </div>
</div>

</body>
</html>
