package com.visa.util;

public class VisaIntegrationConstants {

	public static final String CODIGO_TIENDA = "466246301";
	public static final String CAMPO_COD_TIENDA = "CODTIENDA";
	public static final String CAMPO_NUM_ORDEN = "NUMORDEN";
	public static final String CAMPO_E_TICKET = "ETICKET";
	public static final String CAMPO_E_TICKET_RESPUESTA = "eticket";
	public static final String CAMPO_CANAL = "CANAL";
	public static final String CAMPO_PRODUCTO = "PRODUCTO";
	public static final String CAMPO_MOUNT = "MOUNT";
	public static final String CAMPO_DATO_COMERCIO = "DATO_COMERCIO";
	public static final String CAMPO_ESTADO = "estado";
	public static final String CAMPO_RESPUESTA = "respuesta";	
	public static final String CAMPO_NORDENT = "nordent";
	public static final String CAMPO_COD_ACCION = "cod_accion";
	public static final String CAMPO_PAN = "pan";
	public static final String CAMPO_NOMBRE_TH = "nombre_th";
	public static final String CAMPO_ORI_TARJETA = "ori_tarjeta";
	public static final String CAMPO_NOM_EMISOR = "nom_emisor";
	public static final String CAMPO_ECI = "eci";
	public static final String CAMPO_DSC_ECI = "dsc_eci";
	public static final String CAMPO_COD_AUTORIZA = "cod_autoriza";
	public static final String CAMPO_COD_RESCVV2 = "cod_rescvv2";
	public static final String CAMPO_IMP_AUTORIZADO = "imp_autorizado";
	public static final String CAMPO_FECHAYHORA_TX = "fechayhora_tx";
	public static final String CAMPO_FECHAYHORA_DEPOSITO = "fechayhora_deposito";
	public static final String CAMPO_FECHAYHORA_DEVOLUCION = "fechayhora_devolucion";
	public static final String CAMPO_DATO_COMERCIO_ = "dato_comercio";

	public static final String CAMPO_CANAL_VALOR = "3";
	public static final String CAMPO_PRODUCTO_VALOR = "1";

	public static final String MSG_ERROR_GENERICO = "Ha ocurrido un error, por favor intente m�s tarde.";
	public static final String MSG_ERROR_WEBSERVICE_VISA = "Ha ocurrido un error en la comunicaci�n con Visa, por favor intente m�s tarde.";
	public static final String CAMPO_XML_SERVICE = "xmlService";

	/*
	 * Pagina Master
	 */
	public static final String APP_TITULO_VENTANA = "..:: Universidad Norbert Wiener - Pago electr�nico ::..";
	public static final String APP_TITULO = "Sistema de Pago Electr�nico";
	public static final String LOGIN_CERRAR_SESION = "[ Cerrar sesi�n ]";
	public static final String SEPARADOR_LINKS_CABECERA = "   |   ";
	public static final String IMPRIMIR_TEXTO = "[Imprimir]";
	public static final String VISA_TEXTO = "Esta tienda est� autorizada por Visa para realizar transacciones electr�nicas.";

	/*
	 * Pagina Login
	 */
	public static final String TIPO_USUARIO_ALUMNO = "0";
	public static final String TIPO_USUARIO_POSTULANTE = "1";
	public static final String TIPO_USUARIO_PROSPECTO = "2";

	public static final String LOGIN_USUARIO_ALUMNO = "Nombre de Usuario:";
	public static final String LOGIN_USUARIO_POSTULANTE = "C�digo de Postulante:";
	public static final String LOGIN_USUARIO_PROSPECTO = "Email:";

	public static final String LOGIN_CLAVE_ALUMNO = "Constrase�a:";
	public static final String LOGIN_CLAVE_POSTULANTE = "Nro. Identificaci�n:";
	public static final String LOGIN_CLAVE_PROSPECTO = "Nro. Identificaci�n:";

	public static final String LOGIN_TEXTO = "Iniciar sesi�n para pagos electr�nicos";
	public static final String LOGIN_INICIAR_SESION = " Iniciar Sesi�n ";
	public static final String INSTITUCION_TEXTO = "Instituci�n:";

	/*
	 * Pagina Pagos
	 */
	public static final String PAGINA_PAGOS_TITULO = "Realizar pago electr�nico VISA";
	public static final String CODIGO_USUARIO_TEXTO = "C�digo de ";
	public static final String USUARIO_ALUMNO = "Alumno:";
	public static final String USUARIO_POSTULANTE = "Postulante:";
	public static final String USUARIO_PROSPECTO = "Prospecto:";
	public static final String MENSAJE_ERROR_PERIODO = "El periodo acad�mico debe ser el mismo por cada operaci�n";
	public static final String CLAVE_USUARIO_SESION = "USUARIO_SESION";
	public static final String CLAVE_CARRERA_SESION = "CARRERA_SESION";
	public static final String CLAVE_TIPO_USUARIO_SESION = "TIPO_USUARIO_SESION";
	public static final String CODIGO_SERVICIO_TEXTO = "C�digo Servicio";
	public static final String DESCRIPCION_TEXTO = "Descripci�n";
	public static final String PERIODO_ACADEMICO_TEXTO = "Periodo Acad�mico";
	public static final String CONSIDERACION_TEXTO = "Considere que las cuotas deben ser del mismo periodo acad�mico.";

	/*
	 * Pagina Error
	 */
	public static final String CLAVE_MENSAJE_SESION = "MENSAJE";

	/*
	 * Pagina Respuesta de Visa
	 */
	public static final String CLAVE_RESPUESTA_SESION = "VISA_RESPUESTA";
	public static final String CLAVE_RESPUESTA_ERROR_SESION = "VISA_RESPUESTA_ERROR";
	public static final String PAGINA_REPUESTA_TITULO = "Detalle de la Operaci�n de Pago electr�nico:";
	public static final String NUMERO_PEDIDO_TEXTO = "N�mero Pedido:";
	public static final String IMPORTE_TRANSACCION_TEXTO = "Importe de la transacci�n:";
	public static final String DESCRIPCION_PRODUCTO_TEXTO = "Descripci�n del producto:";
	public static final String CODIGO_COMPRADOR_TEXTO = "C�digo institucional del comprador:";
	public static final String DESCRIPCION_CODIGO_TEXTO = "Descripci�n del c�digo de acci�n:";
	public static final String POLITICAS_DEVOLUCION_TEXTO = "Pol�ticas de Devoluci�n:";
	public static final String TERMINOS_CONDICIONES_TEXTO = "T�rminos y Condiciones:";
	public static final String ERROR_RESPUESTA_VISA = "No se ha podido procesar la solicitud de pago electr�nico. Revise los datos de su tarjeta.";
	public static final String ERROR_PROCESO_SOLICITUD = "No se ha podido procesar la solicitud de pago electr�nico.";
	public static final String CORRECTO_PROCESO_SOLICITUD = "El pago electr�nico se ha efectuado de forma satisfactoria.";
	public static final String ACEPTAR_TERMINOS_CONDICIONES = "Acepto T�rminos y Condiciones";

}
