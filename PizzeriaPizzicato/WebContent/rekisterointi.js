function validoi(e){
	var form = document.querySelector("#rekisterointi_form");
	
	var error = undefined;
	for(element in form.elements){
		var value = escapeHtml(element.value);
		if(element.name == "kayttajatunnus_rek" && value.length < 3){ error="kayttajatunnus on liian lyhyt"; break; }
		if(element.name == "salasana_rek" && value.length < 8){ error="salasana on liian lyhyt"; break; }
		if(element.name == "salasana2_rek" && value.length < 8){ error="salasana ei vastaa ensimmäistä"; break; }
		if(element.name == "osoite_rek" && value.length < 5){ error="osoite on liian lyhyt"; break; }
		if(element.name == "puh_rek" && value.length < 3){ error="puhelinnumero on liian lyhyt"; break; }
		if(element.name == "sahkoposti_rek" && value.length < 3){ error="sahkoposti on liian lyhyt"; break; }
	}
	
	var salasana = form.elements["salasana_rek"];
	var salasana2 = form.elements["salasana2_rek"];
	
	if(salasana !== salasana2){
		e.preventDefault();
		error = "salasanat ei täsmää";
	}
	
	if(error){
		e.preventDefault();
		document.querySelector(".error").innerHTML = error;
		return false;
	}else{
		return true;	
	}
}

function escapeHtml(unsafe) {
    return unsafe
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
 }