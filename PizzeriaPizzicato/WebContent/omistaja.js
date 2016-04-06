(function(){
	
	// Lisää elementille luokka.
	Element.prototype.addClass = function(className){
		var current = this.className.split(" ");
		if(this.className.search(className) != -1){
			current.push(className);
		}
		this.className = current.join(" ");
		return this.className;
	};
	
	// Poista elementiltä luokka.
	Element.prototype.removeClass = function(className){
		var current = this.className.split(" ");
		current.filter(function(name, index){
			return name != className;
		});
		this.className = current.join(" ");
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
	muokkaa(index){

		var nakyma = document.querySelectorAll(".nakyma")[index];
		var muokkaus = document.querySelectorAll(".muokkaus")[index];
		
		if(!muokkaus.hasClass("hidden")){
			nakyma.addClass("hidden");
			muokkaus.removeClass("hidden");
		} else {
			nakyma.removeClass("hidden");
			muokkaus.addClass("hidden");
		}
	};
	
	
})();


