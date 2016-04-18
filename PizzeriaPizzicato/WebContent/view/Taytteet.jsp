<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Täytteet</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script type="text/javascript" src="taytteet.js"></script>
<script type="text/javascript" src="notification.js"></script>
</head>
<body>

<div id="wrapper">	

	<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>

    <h1>Täytteiden muokkaus</h1>
    <h3><a href="Menu">Palaa sivustolle</a></h3>

    <div id="taytelista">
        <table>
        	<tr>
            	<td colspan="5">
                Lisää täyte
                <form class="testi" method="post" action="lisaaTayte">
                    <label>Täytteen nimi</label><input name="nimi" type="text" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä"><br />
                    <label>Täytteen alkuperä</label><input name="alkupera" type="text" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä"><br />
                    <label>Täytteen kuvaus</label><input name="kuvaus" type="text" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä"><br />
                    <label>Täytteen hinta/kg</label><input name="hinta" type="text" pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx">
                    <button type="submit">Lisää</button>
                </form>
                </td>
            </tr>
            <tr style="border-top:1px solid #CCC;">
            	<td>
                	Nimi
                </td>
                <td>
                	Alkuperä
                </td>
                <td>
                	Kuvaus
                </td>
                <td>
                	Hinta
                </td>
                <td>
                </td>
           	</tr>
			<c:forEach items="${taytteet}" var="tayte" varStatus="current">
            <tr>
            	<td colspan="5">
            	<form class="tayteform" method="post" action="MuokkaaTaytetta">
	            	<table class="taytetbl">
	            	<tr>
		            	<td>
						<input disabled type="text" value="${tayte.getNimi()}" name="nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä">
		                </td>
		            	<td>
			            <input disabled type="text" value="${tayte.getAlkupera()}" name="alkupera" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä">
		                </td>
		          		<td>
			            <input disabled type="text" value="${tayte.getKuvaus()}" name="kuvaus" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä">
			            </td>
		                <td>
		                <input disabled type="text" value="${tayte.getHinta()}" name="hinta" pattern="^\d+(\.|\,)\d{2}$" title="Anna hinta numeroina muodossa x.xx">
			            <input type="hidden" name="tayte_id" value="${tayte.getTayte_id()}" class="hidden" />
			            </td>
		                <td>
		                <button class="muokkaabtn" type="button" onClick="muokkaaTayte('${current.index}'); return false;">Muokkaa</button>
		                <button class="tallennabtn hidden" type="submit" value="tallenna">Tallenna</button>
		                <a href="PoistaTayte?id=${tayte.getTayte_id()}">Poista</a>
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
