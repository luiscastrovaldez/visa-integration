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

});

function validarTransaccionVisa() {
	var strCadena = '';
    var intCant = 0;
    var strCuotas = ''; //Variable para verificar el orden de las cuotas seleccionadas
	return true;
}