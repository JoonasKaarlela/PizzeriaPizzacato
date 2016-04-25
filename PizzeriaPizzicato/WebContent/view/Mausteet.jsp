<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mausteet</title>
<link href="styles.css" rel="stylesheet" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
<script type="text/javascript" src="taytteet.js"></script>
<script type="text/javascript" src="notification.js"></script>
</head>
<body>

<div class="notification">
	<c:if test="${requestScope.notification != null}">
		<strong>${requestScope.notification}</strong>
	</c:if>
</div>

<div id="wrapper">	

    <h1>Mausteiden muokkaus</h1>
    <h3><a href="Menu">Palaa sivustolle</a></h3>

    <div id="maustelista">
        <table>
        	<tr>
            	<td colspan="5">
                Lisää mauste
                <form class="testi" method="post" action="lisaaMauste">
                    <label>Mausteen nimi</label><input name="nimi" type="text" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä"><br />
                    <label>Mausteen hinta/g</label><input name="hinta" type="text" pattern="[-+]?[0-9]*[.,]?[0-9]+" title="Anna hinta numeroina">
                    <button type="submit">Lisää</button>
                </form>
                </td>
            </tr>
            <tr style="border-top:1px solid #CCC;">
            	<td>
                	Nimi
                </td>
                <td>
                	Hinta
                </td>
                <td>
                </td>
           	</tr>
			<c:forEach items="${mausteet}" var="mauste" varStatus="current">
            <tr>
            	<td colspan="5">
            	<form class="mausteform" method="post" action="MuokkaaMaustetta">
	            	<table class="maustetbl">
	            	<tr>
		            	<td>
						<input disabled type="text" value="${mauste.getNimi()}" name="nimi" pattern="^\s*([0-9a-zA-Z ]+)\s*$" title="Ei erikoismerkkejä">
		                </td>
		                <td>
		                <input disabled type="text" value="${mauste.getHinta()}" name="hinta" pattern="[-+]?[0-9]*[.,]?[0-9]+" title="Anna hinta numeroina">
			            <input type="hidden" name="mauste_id" value="${mauste.getMauste_id()}" class="hidden" />
			            </td>
		                <td>
		                <button class="muokkaabtn" type="button" onClick="muokkaaMauste('${current.index}'); return false;">Muokkaa</button>
		                <button class="tallennabtn hidden" type="submit" value="tallenna">Tallenna</button>
		                <a href="PoistaMauste?id=${mauste.getMauste_id()}">Poista</a>
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
