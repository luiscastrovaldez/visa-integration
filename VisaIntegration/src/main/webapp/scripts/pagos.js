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
    // validar que existan items seleccionados
	var contador = 0;
	var periodo = '';
	var periodoError = '';
	var cuotasSeleccionadas = '';
	$('#tab div.ui-datatable table tbody td div.ui-chkbox span').each(function() {
	    if($(this).hasClass('ui-icon') && $(this).hasClass('ui-icon-check') && $(this).closest('td').hasClass('cuotaNoPagada')) {
	        contador++;
	        var periodoPago = $(this).closest('tr').find('td.periodo div').text();
			if (periodo == '') {
				periodo = periodoPago;
			} else {
				if (periodoPago != periodo) {
					periodoError = 'El periodo académico debe ser el mismo por cada operación.';
				}
			}
			cuotasSeleccionadas = cuotasSeleccionadas + '|' + $(this).closest('tr').find('td.cuota div').text();
	    }
	});
	if (contador == 0) {
		alert('Por favor seleccione al menos una cuota a pagar.');
		return false;
	}
	// validar que el periodo academico sea el mismo para todas las cuotass seleccionadas
	if (periodoError != '') {
		alert(periodoError);
		return false;
	}
    // validar el orden de las cuotas
	var strCuotas = '';
	$('#tab div.ui-datatable table tbody td.nroDoc div').each(function() {
	    var strNroDoc = $(this).text();
	    if (strNroDoc == '') {
	    	strCuotas = strCuotas + '|' + $(this).closest('tr').find('td.cuota div').text();
	    }
	});
	if (strCuotas.substring(0, cuotasSeleccionadas.length) != cuotasSeleccionadas) {
		alert('Por favor considere el orden de las cuotas al seleccionar los conceptos que pagará.');
	    return false;
    }
	return true;
}