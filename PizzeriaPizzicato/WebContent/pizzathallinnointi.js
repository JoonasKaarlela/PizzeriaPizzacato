// Lisää elementille luokka.
	Element.prototype.addClass = function(className){
		var current = this.className.split(" ");
		current.push(className);
		this.className = current.join(" ");
		return this.className;
	};
	
	// Poista elementiltä luokka.
	Element.prototype.removeClass = function(className){
		var current = this.className.split(" ");
		var updated = current.filter(function(name, index){
			return name != className;
		});
		this.className = updated.join(" ");
		return this.className;
	};
	
	// Tarkista onko elementillä jokin luokka.
	Element.prototype.hasClass = function(className){
		return this.className.search(className) != -1 ? true : false;
	};
	
	// Rekisteröi useampi event
	Element.prototype.on = function(events, cb){
		var events = Array.prototype.split(" ");
		events.map(function(event, index){
			event.addEventListener(event, function(){
				cb();
			});
		});
	};





function muokkaa(id, index){
	var pizza = document.querySelectorAll(".pizza")[index];
	var muokkaa = document.querySelectorAll(".muokkaus")[index];
	
	if(muokkaa.hasClass('hidden')){
		pizza.addClass('hidden');
		muokkaa.removeClass('hidden');
	}else{
		muokkaa.addClass('hidden');
		pizza.addClass('hidden');
	}
}


function validoi(event){
	var form = document.querySelector("#pizza_form");
	
	var error = undefined;
	for(element in form.elements){
		var value = escapeHtml(element.value);
		if(element.name == "nimi" && value.length < 3){ error="nimi on liian lyhyt"; break; }
		if(element.name == "hinta" && ( !Number(value) || value < 0 || value > 99 ) ){ error="hinta on virheellinen"; break; }
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