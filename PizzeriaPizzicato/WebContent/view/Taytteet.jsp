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
</head>
<body>

<div id="wrapper">	

    <h1>Täytteiden muokkaus</h1>
    <h3><a href="Menu">Palaa sivustolle</a></h3>

    <div id="taytelista">
        <table>
        	<tr>
            	<td colspan="5">
                Lisää täyte
                <form class="testi" method="post" action="lisaaTayte">
                    <label>Täytteen nimi</label><input name="nimi" type="text"><br />
                    <label>Täytteen alkuperä</label><input name="alkupera" type="text"><br />
                    <label>Täytteen kuvaus</label><input name="kuvaus" type="text"><br />
                    <label>Täytteen hinta/kg</label><input name="hinta" type="text">
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
						<input disabled type="text" value="${tayte.getNimi()}" name="nimi">
		                </td>
		            	<td>
			            <input disabled type="text" value="${tayte.getAlkupera()}" name="alkupera">
		                </td>
		          		<td>
			            <input disabled type="text" value="${tayte.getKuvaus()}" name="kuvaus">
			            </td>
		                <td>
		                <input disabled type="text" value="${tayte.getHinta()}" name="hinta">
			            <input type="hidden" name="tayte_id" value="${tayte.getTayte_id()}" class="hidden" />
			            </td>
		                <td>
		                <button class="muokkaabtn" type="button" onClick="muokkaaTayte('${current.index}'); return false;">Muokkaa</button>
		                <button class="tallennabtn hidden" type="submit" value="tallenna">Tallenna</button>
		                <button>Poista</button>
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
