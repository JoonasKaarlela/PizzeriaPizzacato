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

	<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>
	
	<div class="error">
          <b> ${error } </b>
    </div>

<div id="wrapper">	

	
	

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
                	Pizzat
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
		                            				<c:forEach items=${pizza.getMausteet( )} var="mauste"> ${mauste.getNimi()}  </c:forEach> 
		                            		
		                            	</c:forEach>
		                            </td>
		                            
		                            <td>
		                            	<c:choose>
		                            		<c:when test="${tilaus.key.getToimitus()}">Kyllä</c:when>
		                            		<c:otherwise>Ei</c:otherwise>
		                            	</c:choose>
		                            </td>
		                            
		                            <td>
		                            	${tilaus.key.getTila()}
		                            </td>
		        
		        					<td>
										<c:choose>
											<c:when test="${tilaus.key.getTila().equals('vastaanotettu')}">
												<button type="submit"> aloita </button>
												<input type="hidden" value="valmistuksessa" name="tila" />
											</c:when>
											<c:otherwise>
												<button type="submit"> valmis </button>
												<c:if test="${!tilaus.key.getToimitus()}"> <input type="hidden" value="noudettavissa" name="tila" /> </c:if>
		               							<c:if test="${tilaus.key.getToimitus()}"> <input type="hidden" value="odottaa toimitusta" name="tila" /> </c:if>
											</c:otherwise>
										</c:choose>
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