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

    <h1> Hallintasivu </h1>
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
    
    <div class='main'>
    	
    	<form class='MuokkaaPizzaa' action='MuokkaaPizzaa' method='POST'>
    		
    		<input value="${pizza.getNimi()}" name='nimi' />
    		<input value="${pizza.getKuvaus()}" />
    		
    		<button type='submit'> Tallenna </button>
    	
	    	<table>
	    		<thead>
	    			<tr>
	    				<td>Nimi</td>
	    				<td>Alkuperä</td>
	    				<td>Kuvaus</td>
	    				<td>Hinta</td>
	    				<td>Valittu</td>
	    			</tr>
	    		</thead>
	    		<tbody>
		    		<c:forEach items='${pizza.getTaytteet()}' var='tayte'>
		    			<tr>
		    				<td> ${tayte.getNimi()} </td>
		    				<td> ${tayte.getAlkupera()}</td>
		    				<td> ${tayte.getKuvaus()}</td>
		    				<td> ${tayte.getHinta()}</td>
		    				<td>
		    					<c:choose>
			    					<c:when test='${pizza.getTaytteet().contains(tayte)}'>
			    						<input type='checkbox' value='${tayte.getTayte_id()}' checked='checked' name='tayte' />
			    					</c:when>
			    					<c:otherwise>
			    						<input type='checkbox' value='${tayte.getTayte_id()}' name='tayte' />
			    					</c:otherwise>
		    					</c:choose>
		    				</td>
		    			</tr>
		    		</c:forEach>
	    		</tbody>	
	    	</table>
	    	
    	</form>
    </div>
    
    
</div>

<script type="text/javascript" src="pizzathallinnointi.js"></script>
</body>
</html>
