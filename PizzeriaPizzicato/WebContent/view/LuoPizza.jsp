<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hallintasivu - Pizza - Uusi</title>
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
    	
    	<h2>Lisää pizza</h2>
		                
		                <!-- LISÄÄ PIZZA -->
		                <div style="margin-bottom:50px;">
		                    <form method="post" action="LisaaPizza">
		                        <label>Pizzannimi</label><input name="nimi" placeholder="pizzan nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä" size="15" required /><br />
		                        <label>Pizzan kuvaus</label><input name="kuvaus" placeholder="kuvaus" size="30" required/><br />
		                        <label>Pizzan hinta</label><input name="hinta" placeholder="hinta" pattern="[-+]?[0-9]*[.,]?[0-9]+" title="Anna hinta numeroina muodossa x.xx" size="4" required/><br />
		                        <h3>Täytteet:</h3>
		                       <div id="LisaaPizzaTaytteet">
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
		                       </div>
						    	<label for="listalla"> Listalla </label>
		                        <input type=checkbox name="listalla" />
		                        <button type="submit"> Lisää </button>
		                       </form>
		                      </div>
    
    </div>
</div>

<script type="text/javascript" src="pizzathallinnointi.js"></script>
</body>
</html>

