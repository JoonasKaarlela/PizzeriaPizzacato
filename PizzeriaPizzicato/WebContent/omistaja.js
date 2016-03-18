"use strict";
if( document.readyState == "complete"){
	
	// Muutama helpperi funktio
	Element.prototype.addClass = function(name){
		var names = Array.prototype.split.call(this.className, " ");
		names.push(name);
		this.className = names.join(" ");
	}
		
	Element.prototype.removeClass = function(newName){
		var names = Array.prototype.split.call(this.className, " ");
		names.filter(function(currentName, index){
			return newName =! currentName;
		});
		this.className = names.join(" ");
	}
		
	Element.prototype.hasClass = function(name){
		var	names = Array.prototype.split.calss(this.className, " ");
		var match = names.filter(function(currentName, index){
			return currentName == name;
		});
		return match.length > 0;
	}
	
	
	function muokkaa(pizza_id, index){
		
		/*
		// UUSI PAREMPI
		var elementit = Array.from(document.querySelectorAll(".pizzainfo"))[index].children;
		elementit.forEach(function(elementti){
			Array.from(elementti.children).map(function(child){
				if(child.tagName == "input" || child.tagName == "textarea"){
					child.removeAttribute("disabled");
				}
				if(child.hasClass(".poista")){
					child.removeClass("hidden");
					child.addEventListener("click", function(){
						var request = new XMLHttpRequest();
						request.onreadystatechange = function(){
							if(request.readyState == 4 && request.status == 200){
								location.reload();
							}
						}
						request.open("POST", "poista", true);
						request.send("id="+pizza_id);
					});
				}
			});
			if(elementti.hasClass("muokkaa") || elementti.hasClass("listalla")){
				elementti.removeClass("hidden");
			}
			if(elementti.hasClass("muokkaus")){
				elementti.addClass("hidden");
			}
		});
		*/
		
		
		
		// VANHA TOIMIVA
		// Aktivoi "input" kentät
		$('input', $(".pizza")[index]).each(function(){
			$(this).removeAttr('disabled');
			$(this).addClass('muokkaus');
		});
		
		// Aktivoi "textarea" kenttä
		$('textarea', $(".pizza")[index]).each(function(){
			$(this).removeAttr('disabled');
			$(this).addClass('muokkaus');
		});
		
		// Muuta button tyyppi ja teksti
		$('.muokkaa', $('.pizzainfo')[index]).each(function(){
			$(this).addClass('hidden');
			$(this).text("tallenna");
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
}



