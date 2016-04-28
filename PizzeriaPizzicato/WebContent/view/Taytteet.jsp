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
        	<li class="aktiivinen">
            	<a href="TaytteidenHallinta">Täytteet</a>
        	</li>
            <li>
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
                <h2>Lisää tayte</h2>
		                
		                <!-- lisää täyte -->
		                <div>
		                    <form method="post" action="LisaaTayte">
		                        <label>nimi</label><input name="nimi" placeholder="taytteen nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" /><br />
		                        <label>alkuperä</label><input name="alkupera" placeholder="taytteen alkuperä maa" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" /><br />
		                        <label>kuvaus</label><input name="kuvaus" placeholder="kuvaus" /><br />
		                        <label>hinta</label><input name="hinta" placeholder="hinta" pattern="[-+]?[0-9]*[.,]?[0-9]+" title="Anna hinta numeroina muodossa x.xx" /><br />
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
                	alkuperä
                </td>
                <td>
                	kuvaus
                </td>
                <td>
                	hinta
                </td>
                <td>
                </td>
           	</tr>
           	<c:forEach items="${taytteet}" var="tayte" varStatus="current">
            <tr>
            	<td colspan="5">
                    <form class="pizzaform" id="pizzaform" method="post" action="MuokkaaTaytetta" onSubmit="validoi(event)">
                    <div class="error"></div>
                   	<div class="pizza">
                    	<table>
	                        <tr>
	                        	<td>
	                            	${tayte.getNimi()}
	                            </td>
	                             <td>
	                            	${tayte.getKuvaus()}"
	                            </td>
	                            <td>
	                            	${tayte.getKuvaus()}
	                            </td>
	                            <td>
	                            	${tayte.getHinta()}
	                            </td>
	                            
	                            <td rowspan="2" align="right">
	                            	<button onClick="muokkaa('${current.index}'); return false;"> Muokkaa </button>
	                            </td>

	                        </tr>              	
                        </table>
                        </div>
                        
                        <div class="muokkaus hidden">
	                        <table>
		                        <tr>
		                        	<td>
		                            	<input type="hidden" value="${tayte.getTayte_id()}" name="tayte_id">
		                            	<input type="text" value="${tayte.getNimi()}" name="nimi">
		                            </td>
		                             <td>
		                            	<input  type="text" value="${tayte.getKuvaus()}" name="kuvaus">
		                            </td>
		                            <td>
		                            	<input type="text" value="${tayte.getKuvaus()}" name="kuvaus">
		                            </td>
		                            <td>
		                            	<input type="text" value="${tayte.getHinta()}" name="hinta">
		                            </td>
		                            
		                            <td rowspan="2" align="right">
		                            	<button type="submit"> Tallenna </button>
		                            	<a href="PoistaTayte?tayte_id=${tayte.getTayte_id()}"> Poista </a>
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
