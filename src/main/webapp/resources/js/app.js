$(function() {
	
	/*Layout Functions*/
	
	$('.js-toggle').bind('click', function(event) {
		$('.js-sidebar, .js-content').toggleClass('is-toggled');
		event.preventDefault();
	});

	$('.js-userinfo-dropdown').bind('click', function(event){
		$('.js-userinfo-dropdown').toggleClass('selected');
		$('.js-userinfo-navbar').toggleClass('is-toggled');
		event.preventDefault();
	});

});
