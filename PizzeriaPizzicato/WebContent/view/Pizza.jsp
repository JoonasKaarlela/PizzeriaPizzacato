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
    		
    		<h2> Muokkaa: ${pizza.getPizza_id()} </h2>
    		
    		<div>
	    		<div>
	    			<label for="nimi"> Nimi </label>
	    			<input name="nimi" value="${pizza.getNimi()}" placeholder="nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" size="15" required />
	    		</div>
	    		<div>
	    			<label for="kuvaus"> Kuvaus </label>
	    			<input name="kuvaus" value="${pizza.getKuvaus()}" placeholder="kuvaus" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" size="30" required />
	    		</div>
	    		<div>
	    			<label for="hinta"> Hinta </label>
	    			<input name="hinta" value="${pizza.getHinta()}" placeholder="hinta" pattern="[-+]?[0-9]*[.,]?[0-9]+" title="Anna hinta numeroina muodossa x.xx" size="4" required/>
	    		</div>
	
				<div>
			        <c:choose>
			        	<c:when test="${pizza.getListalla()}">
			        		<label for="listalla"> Listalla </label>
			        		<input type=checkbox name="listalla" id="listalla" checked="checked" />
			        	</c:when>
			        	<c:otherwise>
			        		<label for="listalla"> Listalla </label>
			        		<input type=checkbox name="listalla" id="listalla"  />
			        	</c:otherwise>
			        </c:choose>
			    </div>
    		</div>
    	
    		<div>
    		<h3>Valitse täytteet:</h3>
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
		    		<c:forEach items='${taytteet}' var='tayte'>
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
	    	<div>
	    		<button type='submit'> Tallenna </button>
	    		<a href='PizzojenHallinta' style='color:crimson;'> Peruuta </a>
	    	</div>
	    	</div>
	    	<input type='hidden' value="${pizza.getPizza_id()}" name='id' />
    	</form>
    </div>
    
    
</div>

<script type="text/javascript" src="hallinnointi.js"></script>
</body>
</html>
