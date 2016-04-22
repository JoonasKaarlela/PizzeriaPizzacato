Element.prototype.addClass = function(className){
		var current = this.className.split(" ");
		current.push(className);
		this.className = current.join(" ");
		return this.className;
};

setTimeout(function(){
	var notification = document.querySelector(".notification");
	notification.addClass("hidden");
}, 3000);