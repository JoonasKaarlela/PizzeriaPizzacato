<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Fantasia</title>
		<link href="styles.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<script type="text/javascript" src="notification.js"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js"></script>
   </head>
<body>

	<div class="notification">
		<c:if test="${requestScope.notification != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>
	
	<div class="error">
		<c:if test="${error != null}">
			<strong>${requestScope.notification}</strong>
		</c:if>
	</div>

	<div id="wrapper">

		<div id="header">
			<div id="logo">
				<img src="pizzerialogo-lapinakyva.png" width="50" />PizzeriaPizzicato
			</div>

			<div id="kirjaudu">
				<c:choose>

					<c:when test="${sessionScope.kayttaja != null}">
						<p>
							<strong> <i class="fa fa-user"></i> ${sessionScope.kayttaja.getKayttajatunnus()} </strong>
						</p>
						<div>
							<a href="kirjauduUlos"> kirjaudu ulos </a>
						</div>
					</c:when>
					

					<c:otherwise>
						<form method=post action="Kirjaudu" id="kirjaudu_form">
							<div id="kayttajatunnus">
								<div>
									<input placeholder=kayttajatunnus name=kayttajatunnus required />
								</div>
							</div>
							<div id="salasana">
								<div>
									<input placeholder=salasana name=salasana type=password
										required />
								</div>
							</div>
							<div id="submit" style="display: inline-block;">
								<div>
									<button type=submit>kirjaudu</button>
								</div>
								<div>
									<a href="Rekisterointi"> rekisteroidy </a>
								</div>
							</div>
						</form>
						<div class="error">
							<p style="color: crimson">
								<c:out value="${error}"></c:out>
							</p>
						</div>
					</c:otherwise>

				</c:choose>
				
				<div id="yhteystiedot">
					<p>
						<b>Yhteystiedot</b><br> Pasilanraitio 10 D92<br> 00240
						Helsinki <br> 040 840 0987
					</p>
				</div>
				<div id="aukioloajat">
					<p>
						<b>Aukioloajat</b><br> Ma-To 09:00 - 22:30<br> Pe 09:00
						- 02:00<br> La 09:00 - 02:00<br> Su 10:00 - 22:30
					</p>
				</div>
				
			</div>

			<!--  NAVIGOINTI -->
			<div class="clear"></div>
			<div id="nav">
				<ul>
					<li><a href="Menu">Menu</a></li>
					<li><a href="Yhteystiedot">Yhteystiedot</a></li>
					<li><a href="Ostoskori">Ostoskori</a> </li>

					<c:if test="${sessionScope.kayttaja != null}">
						<li><a href="Tilaukset">Tilaukset</a></li>
					</c:if>
					
					<c:if test="${sessionScope.kayttaja.isOmistaja()}">
						<li><a href="PizzojenHallinta"> Hallinnoi </a> </li>
					</c:if>
					
					<c:if test="${sessionScope.kayttaja.isKuljettaja()}">
						<li><a href="Kuljettaja"> Kuljetukset </a></li>
					</c:if>
					
					<c:if test="${sessionScope.kayttaja.isKokki()}">
						<li><a href="Kokki"> Odottavat </a> </li>
					</c:if>

				</ul>
			</div>
			<!-- NAV LOPPUU -->
		</div>

		<div class="clear"></div>

		<!--  BANNERI -->
		<div id="banner">
			<img src="banner.jpg">
		</div>

		<!--  PIZZA MENU ALKAA -->
		<div id="content">
			<div id="content-left">
            	<div class="pkuva">
            		<div class="pizzanimi">Fantasia</div>
                	<img src="pizza1.png">
                </div>
            	<div class="pkuvaus">
                	Kuvaus
                </div>
            </div>
            
            <div id="content-right">
            	<form method="post" action="lisaaKoriin">
	            	<div class="ptayte">
	            	<h3>Täytteet</h3>
	            	<h3>Lisätäytteet</h3>
	                	<table id="tbl">
						    <tbody>
						      <tr>
						        <td>
						        	<select name="taytteet" required>
						        		<option disabled selected>Valitse täyte</option>
						        		<c:forEach items="${taytteet}" var="tayte" varStatus="current">
						             	<option value="${tayte.getTayte_id()}"> ${tayte.getNimi()} </option>
						                </c:forEach>
						          	</select>
						        </td>
						        <td>
						          	<input type="button" class="add-row" value="+"/>
						          	<input type="button" class="del-row" value="x"/>
						        </td>
						      </tr>
						    </tbody>
						  </table>
	                </div>
	            	<div class="pmauste">
	            	<h3>Mausteet</h3>
	                	<c:forEach items="${mausteet}" var="mauste" varStatus="current">
			                <input type="checkbox" value="${mauste.getMauste_id()}"><label>${mauste.getNimi()}</label>
			            </c:forEach>
	                </div>
	                <button type="submit" class="ptilaa">Tilaa</button>
	                Hinta: 0,00€
           		</form>
           	</div>
            
		</div>
		<!-- CONTENT LOPPUU -->
	<style type="text/css">
			.pkuva {
				text-align: center;
			}
			
			#content {
				margin:auto;
				width:90%;
			}
			#content-left {
				float: left;
				width: 48%;
				padding: 1%;
			}
			#content-right {
				float: left;
				width:48%;
				padding: 1%;
			}
        </style>
	</div>

<script>
		function addRow() {
		   var row = $('tbody tr:first').clone();
		   row.find('input[type!=button]').val('');
		   $('#tbl tbody').append(row);
		}
		function delRow() {
			$(this).closest('tr').remove();
		}
		
		$('#tbl').on('click', '.del-row', delRow);
		
		$('#tbl').on('click', '.add-row', addRow);
		
		$('#tbl').on('change', 'input', function() {
		  if($(this).val() != '' &&
		     $(this).closest('tr').is(':last-child')) {
		    addRow();
		  }
		});
		</script>
</body>
</html>
