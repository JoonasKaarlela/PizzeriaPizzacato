<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hallintasivu - Pizzat</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<script type="text/javascript" src="hallinnointi.js"></script>
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
    <h3><a href="Menu">  <i class="fa fa-arrow-left"></i> Takaisin</a></h3>
	<div id="hallintanav">
    	<ul>
        	<li>
            	<a href="TaytteidenHallinta">Täytteet</a>
        	</li>
            <li>
            	<a href="PizzojenHallinta">Pizzat</a>
            </li>
            <li class="aktiivinen">	
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
                <h2> Tilaukset </h2>
            </tr>
            
            <tr style="border-top:1px solid #CCC;">
            	<td>
                	ID
                </td>
                <td>
                	Kayttaja
                </td>
                <td>
                	Tilausaika
                </td>
                <td>
                	Hinta
                </td>
                <td>
                	Toimitus
                </td>
                <td>
                	Tila
                </td>
                <td>
                </td>
           	</tr>
           	
           	<c:forEach items="${tilaukset}" var="tilaus" varStatus="current">
           	
            <tr>
            	<td colspan="5">
                    <form class="pizzaform" id="pizzaform" method="post" action="MuokkaaTilausta" >
                    <div class="error"></div>
                   	<div class="pizza">
	                    	<table>
		                        <tr>
		                        	<td>
		                            	${tilaus.key.getTilaus_id()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.key.getKayttaja().getKayttajatunnus()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.key.getTilausaika()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.key.getHinta()}
		                            </td>
		                            
		                            <td>
		                            	<c:choose>
		                            		<c:when test="${tilaus.key.getToimitus() }">kyllä</c:when>
		                            		<c:otherwise>ei</c:otherwise>
		                            	</c:choose>
		                            </td>
		        
		        					<td>
		                            	${tilaus.key.getTila()}
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
		                        		<input type="hidden" value="${tilaus.key.getTilaus_id()}" name="id" />
		                            	${tilaus.key.getTilaus_id()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.key.getKayttaja().getKayttajatunnus()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.key.getTilausaika()}
		                            </td>
		                            
		                            <td>
		                            	<c:forEach items="${tilaus.value}" var="pizza">
		                            		${pizza.getNimi()}
		                            	</c:forEach>
		                            </td>
		        
		        					<td>
		                            	<select name="tila">
		                            		<c:if test="${tilaus.key.getToimitus()}"> <option value="odottaa toimitusta"> odottaa toimitusta </option> </c:if>
		                            		<c:if test="${!tilaus.key.getToimitus()}"><option value="noudettavissa"> noudettavissa </option></c:if>
		                            	</select>
		                            </td>
		                            
		                            <td rowspan="2" align="right">
		                            	<button type="submit"> Tallenna </button>
		                            	<a href="PoistaTilaus?tilaus_id=${tilaus.key.getTilaus_id()}" style="color:crimson;"> Poista </a>
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


<script type="text/javascript" src="hallinnointi.js"></script>
</body>
</html>