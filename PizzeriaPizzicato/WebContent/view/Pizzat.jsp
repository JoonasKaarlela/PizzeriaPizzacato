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
<script type="text/javascript" src="notification.js"></script>
<script type="text/javascript" src="taytteet.js"></script>

</head>
<body>

<div id="wrapper">	

		<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>
	
	<div class="error">
          <b> ${error } </b>
    </div>
	

    <h1>Hallintasivu</h1>
    <h3><a href="Menu"> <i class="fa fa-arrow-left"></i> Takaisin </a></h3>
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
    	<div id="actionbar">
    		<div class="action"> <a href="UusiPizza" style="color:lightgreen;"> <i class="fa fa-plus"></i> </a> </div>
    	</div>
        <table>
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
                    <form class="pizzaform" id="pizzaform" method="post" action="MuokkaaPizzaa" onSubmit="validoi(event)">
                    <div class="error"></div>
                   	<div class="pizza">
                    	<table>
	                        <tr>
	                        	<td>
	                            	${pizza.getNimi()}
	                            </td>
	                            <td>
	                            	${pizza.getKuvaus()}
	                            </td>
	                            <td>
	                            	${pizza.getHinta()}
	                            </td>
	                            <td>
	                            	<c:forEach items="${pizza.getTaytteet()}" var="tayte" varStatus='current'>
	                            		${tayte.getNimi()}<c:if test="${current.index < pizza.getTaytteet().size()-1}">,</c:if> 
	                            	</c:forEach>
	                            </td>
	                            <td>
	                            	<c:choose>
	                            		<c:when test="${pizza.getListalla()}">
	                            			<input disabled type="checkbox" name="listalla" checked="checked">
	                            		</c:when>
	                            		<c:otherwise>
	                            			<input disabled type="checkbox" name="listalla">
	                            		</c:otherwise>
	                            	</c:choose>
	                            </td>
	                            
	                            <td rowspan="2" align="right">
	                            	<a href='MuokkaaPizzaaTila?id=${pizza.getPizza_id()}' style='font-size:15px;'>  muokkaa </a>
	                            	<a href="PoistaPizza?id=${pizza.getPizza_id()}" style="color:crimson; font-size:15px;">  poista </a>
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
