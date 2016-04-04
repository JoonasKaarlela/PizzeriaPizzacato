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
		
		$('.pizzantayte', $('.pizzainfo')[index]).each(function(){
			$(this).addClass('hidden');
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

		// Peruuta muokkaus
		$('.peruuta', $('.pizzainfo')[index]).each(function(){
			$(this).removeClass('hidden');
			$(this).click(function(){
				location.reload();
			});
		});
}


