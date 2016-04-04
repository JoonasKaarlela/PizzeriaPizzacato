<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Täytteet</title>
<link href="styles.css" rel="stylesheet" type="text/css">
</head>
<body>

<div id="wrapper">	
    <h1>Täytteiden muokkaus</h1>
    <h3>Palaa sivustolle</h3>

    <div id="taytelista">
        <table>
        	<tr>
            	<td colspan="5">
                Lisää täyte
                <form>
                    <label>Täytteen nimi</label><input type="text"><br />
                    <label>Täytteen alkuperä</label><input type="text"><br />
                    <label>Täytteen kuvaus</label><input type="text"><br />
                    <label>Täytteen hinta/kg</label><input type="text">
                    <button>Lisää</button>
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
			<c:forEach items="${taytteet}" var="tayte">
            <tr>
            	<td>
				<input disabled type="text" placeholder="${tayte.getNimi()}">
                </td>
            	<td>
	            <input disabled type="text" placeholder="${tayte.getAlkupera()}">
                </td>
          		<td>
	            <input disabled type="text" placeholder="${tayte.getKuvaus()}">
	            </td>
                <td>
                <input disabled type="text" placeholder="${tayte.getHinta()}">
	            </td>
                <td>
                <button>Muokkaa</button><button>Poista</button>
        		</td>
         	</tr>
			</c:forEach>
    	</table>
    </div>
</div>

</body>
</html>