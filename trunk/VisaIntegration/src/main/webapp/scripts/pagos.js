jQuery(function($) {
	$('#tabs').tabs( {
		select : function(event, ui) {
			var url = $.data(ui.tab, 'load.tabs');

			if (url) {
				location.href = url;
				return false;
			}

			return true;
		}
	});

	var $tabs = $('#tabs').tabs();
	$tabs.tabs('select', 0);

	var floMontoTotal = 0;

	$('.monto').each(function() {
		floMontoTotal += parseFloat($(this).text());
	});

	$('#<%=txtMontoTotal.ClientID %>').val(floMontoTotal.toFixed(2));
});
