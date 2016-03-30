	// lisaa luokka elementille
	Element.prototype.addClass = function(name){
		var names = Array.prototype.split.call(this.className, " ");
		names.push(name);
		this.className = names.join(" ");
	}
		
	// poita luokka elementilta
	Element.prototype.removeClass = function(newName){
		var names = Array.prototype.split.call(this.className, " ");
		names.filter(function(currentName, index){
			return newName =! currentName;
		});
		this.className = names.join(" ");
	}
		
	// tsekkaa onko elementilla luokka
	Element.prototype.hasClass = function(name){
		var	names = Array.prototype.split.calss(this.className, " ");
		var match = names.filter(function(currentName, index){
			return currentName == name;
		});
		return match.length > 0;
	}
	
	
	function muokkaa(pizza_id, index){

		// Aktivoi "input" kentät
		$('input', $(".pizza")[index]).each(function(){
			$(this).removeAttr('disabled');
		});
		
		// Aktivoi "textarea" kenttä
		$('textarea', $(".pizza")[index]).each(function(){
			$(this).removeAttr('disabled');
		});
		
		// Muuta button tyyppi ja teksti
		$('.muokkaa', $('.pizza')[index]).each(function(){
			$(this).addClass('hidden');
		});
		
		$('.muokkaus', $('.pizza')[index]).each(function(){
			$(this).removeClass('hidden');
		});
		
		// Tallenna nappi näkyviin
		$('.tallenna', $('.pizzainfo')[index]).each(function(){
			$(this).removeClass('hidden');
		});
		
		// Listalla nappi näkyviin
		$('.listalla', $('.pizzainfo')[index]).each(function(){
			$(this).removeClass('hidden');
		});
		
		// Rekisteröi poista pizza funktio
		$('.poista', $('.pizzainfo')[index]).each(function(){
			$(this).removeClass('hidden');
			$(this).click(function(){
					$.post("poista", {id:pizza_id}, function(){
						location.reload();
					});
				});
		});

	}


