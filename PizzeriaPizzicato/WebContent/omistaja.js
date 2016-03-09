console.log("loaded9");
function muokkaa(id){

	// enable inputs
	var index = id - 1;
	$('input', $(".pizza")[index]).each(function(){
		$(this).removeAttr('disabled');
		$(this).addClass('muokkaus');
	});
	
	// enable textarea
	$('textarea', $(".pizza")[index]).each(function(){
		$(this).removeAttr('disabled');
		$(this).addClass('muokkaus');
	});
	
	// override button function
	$('.muokkaa', $('.pizzainfo')[index]).each(function(){
		$(this).text("tallenna");
		// tallenna funktio
		
	});
	
	// poista
	$('.poista', $('.pizzainfo')[index]).each(function(){
		$(this).css('display', 'block');
		$(this).click(function(){
			var answer = prompt("Are you sure? ( type yes if sure)", "");
			if( answer == "yes"){
				// poista pizza
			}
		});
	});
	
	// piilota
	

}