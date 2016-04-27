<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hallintasivu - Pizzat</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<script type="text/javascript" src="pizzathallinnointi.js"></script>
<script type="text/javascript" src="taytteet.js"></script>

</head>
<body>

<div id="wrapper">	

    <h1>Hallintasivu</h1>
    <h3><a href="Menu">Palaa pääsivulle</a></h3>
	<div id="hallintanav">
    	<ul>
        	<li>
            	<a href="TaytteidenHallinta">Täytteet</a>
        	</li>
            <li class="aktiivinen">
            	<a href="PizzojenHallinta">Pizzat</a>
            </li>
            <li>	
            	<a href="TilaustenHallinta">Tilaukset</a>
            </li>
            <li>
            	<a href="MausteidenHallinta"> Mausteet </a>
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
		                        <label>Pizzan hinta</label><input name="hinta" placeholder="hinta" pattern="[-+]?[0-9]*[.,]?[0-9]+" title="Anna hinta numeroina muodossa x.xx" /><br />
		                        <label>Pizzan täytteet</label><select multiple name="taytteet">
		                        	<c:forEach items="${taytteet}" var="tayte" varStatus="current">
		                        		<label> Täyte ${current.index} </label><option value="${tayte.getTayte_id()}"> ${tayte.getNimi()} </option>
		                        	</c:forEach>
		                        </select>
		                        <button type="submit"> Lisää </button>
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
                	Taytteet
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
                    <form class="pizzaform" id="pizzaform" method="post" action="muokkaa" onSubmit="validoi(event)">
                    <div class="error"></div>
                   	<div class="pizza">
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
	                            	<c:forEach items="${pizza.getTaytteet()}" var="tayte">
	                            		${tayte.getNimi()}
	                            	</c:forEach>
	                            </td>
	                            <td>
	                            	<label for="listalla"> Listalla </label>
	                            	<input disabled type="checkbox" checked="${pizza.getListalla()}" value="${pizza.getListalla()}" name="listalla">
	                            </td>
	                            
	                            <td rowspan="2" align="right">
	                            	<button onClick="muokkaa('${pizza.getPizza_id()}', '${current.index}'); return false;"> Muokkaa</button>
	                            </td>

	                            
	                        </tr>              	
                        </table>
                        </div>
                        
                        <div class="muokkaus hidden">
                        <table>
	                        <tr>
	                        	<td>
	                            	<input type="hidden" value="${pizza.getPizza_id()}" name="pizza_id">
	                            	<input  type="text" value="${pizza.getNimi()}" name="nimi">
	                            </td>
	                            <td>
	                            	<input  type="text" value="${pizza.getKuvaus()}" name="kuvaus">
	                            </td>
	                            <td>
	                            	<input type="text" value="${pizza.getHinta()}" name="hinta">
	                            </td>
	                            <td>
	                        		<select name="taytteet">
	                        			<c:forEach items="${pizza.getTaytteet()}" var="tayte" varStatus="status">
	                        				<option value="${tayte.getNimi()}">${tayte.getNimi()}</option>
	                        			</c:forEach>
	                        		</select>
	                            </td>
	                            <td>
	                            	<label for="listalla"> Listalla </label>
	                            	<input type="checkbox" checked="${pizza.getListalla()}" value="${pizza.getListalla()}" name="listalla">
	                            </td>
	                            
	                            <td rowspan="2" align="right">
	                            	<button type="submit"> Tallenna</button>
	                            	<a href="poista?id=${pizza.getPizza_id()}"> Poista </a>
	                            </td>

	                        </tr>                    	
                        </table>
                        </div>
                        
                    </form>
                </td>
            </tr>
            </c:forEach>
    	</table>
    </div>
</div>

<script type="text/javascript" src="pizzathallinnointi.js"></script>
</body>
</html>
