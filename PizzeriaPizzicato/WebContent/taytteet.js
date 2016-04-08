function muokkaaTayte(index){
		$($('.muokkaabtn')[index]).addClass('hidden');
		$($('.tallennabtn')[index]).removeClass('hidden');
		$('.tayteform :input').prop("disabled", false);
};
	
