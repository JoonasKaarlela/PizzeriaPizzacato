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
            <li class="aktiivinen">	
            	<a href="TilaustenHallinta">Tilaukset</a>
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
                	Osoite
                </td>
                <td>
                	Puh
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
		                        		<input type="hidden" value="${tilaus.getTilaus_id()}" name="id" />
		                            	${tilaus.getTilaus_id()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.getKayttaja().getKayttajatunnus()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.getTilausaika()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.getHinta()}€
		                            </td>
		                            
		                            <td>
		                            	${tilaus.getOsoite()}
		                            </td>
		                            
		                            <td>
		                            	${tilaus.getPuh()}
		                            </td>

		        					<td>
		                            	<c:choose>
											<c:when test="${tilaus.getTila().equals('odottaa toimitusta')}">
												<button type="submit"> aloita </button>
												<input type="hidden" value="toimituksessa" name="tila" />
											</c:when>
											<c:otherwise>
												<button type="submit"> valmis </button>
												 <input type="hidden" value="valmis" name="tila" />
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