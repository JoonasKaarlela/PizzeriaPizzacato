	
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
	
	// Funktiot
	function muokkaa(index){
		
		// Valitse muokattava näkymä
		var nakyma = document.querySelectorAll(".nakyma")[index];
		
		// Valitse muokattavan näkymän muokkaus container
		var muokkaus = document.querySelectorAll(".muokkaus")[index];
		
		// Toggle	
		if(muokkaus.hasClass("hidden")){
			nakyma.addClass("hidden");
			muokkaus.removeClass("hidden");
		} else {
			nakyma.removeClass("hidden");
			muokkaus.addClass("hidden");
		}
	};
	
