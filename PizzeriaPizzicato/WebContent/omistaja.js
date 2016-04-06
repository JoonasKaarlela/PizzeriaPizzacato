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
		var nakyma = document.querySelectorAll(".nakyma")[index];
		console.log(nakyma);
		
		var muokkaus = document.querySelectorAll(".muokkaus")[index];
		console.log(muokkaus);
		
		if(muokkaus.hasClass("hidden")){
			console.log("muokkaus");
			nakyma.addClass("hidden");
			muokkaus.removeClass("hidden");
		} else {
			console.log("nakyma");
			nakyma.removeClass("hidden");
			muokkaus.addClass("hidden");
		}
	};
	
