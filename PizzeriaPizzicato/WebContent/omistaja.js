function muokkaa(pizza_id, index){
	
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
	
	// Rekisteröi poista pizza funktio
	$('.poista', $('.pizzainfo')[index]).each(function(){
		$(this).removeClass('hidden');
		$(this).click(function(){
			var vastaus = prompt("Oletko varma? ( kyllä / ei )", "");
			if( vastaus == "kyllä"){
				$.post("poista", {id:pizza_id}, function(){
					location.reload();
				});
			}
		});
	});

}