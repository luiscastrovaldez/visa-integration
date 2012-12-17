package com.visa.util;

import java.util.HashMap;
import java.util.Map;

public class VisaIntegrationConstants {

	public static final String CODIGO_TIENDA = "466246301";
	public static final String CAMPO_COD_TIENDA = "CODTIENDA";
	public static final String CAMPO_NUM_ORDEN = "NUMORDEN";
	public static final String CAMPO_E_TICKET = "ETICKET";
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

	 /*
   * Codigos de Accion
   */
  public static final Map<String, String> CODIGOSACCIONMAP = new HashMap<String, String>() {

    {
      put("101", "Operaci�n Denegada. Tarjeta Vencida.");
      put("102", "Operaci�n Denegada. Contactar con emisor.");
      put("104", "Operaci�n Denegada. Operacion no permitida para esta tarjeta.");
      put("106", "Operaci�n Denegada. Intentos de pin excedidos.");
      put("107", "Operaci�n Denegada. Contactar con el emisor.");
      put("108", "Operaci�n Denegada. Exceso de actividad.");
      put("109", "Operaci�n Denegada. Identificaci�n inv�lida de establecimiento.");
      put("110", "Operaci�n Denegada. Operacion no permitida para esta tarjeta.");
      put("111", "Operaci�n Denegada. Operaci�n Denegada. El monto de la transacci�n supera el valor m�ximo permitido para operaciones virtuales.");
      put("112", "Operaci�n Denegada. Se requiere clave.");
      put("116", "Operaci�n Denegada. Fondos insuficientes.");
      put("117", "Operaci�n Denegada. Clave incorrecta.");
      put("118", "Operaci�n Denegada. Tarjeta Inv�lida.");
      put("119", "Operaci�n Denegada. Exceso de intentos de ingreso de PIN.");
      put("121", "Operaci�n Denegada.");
      put("126", "Operaci�n Denegada. PIN inv�lido.");
      put("129", "Operaci�n Denegada. Tarjeta no operativa.");
      put("180", "Operaci�n Denegada. Tarjeta inv�lida.");
      put("181", "Operaci�n Denegada Tarjeta con restricciones de D�bito.");
      put("182", "Operaci�n Denegada. Tarjeta con restricciones de Cr�dito.");
      put("183", "Operaci�n Denegada. Error de sistema.");
      put("190", "Operaci�n Denegada. Contactar con emisor.");
      put("191", "Operaci�n Denegada. Contactar con emisor.");
      put("192", "Operaci�n Denegada. Contactar con emisor.");
      put("199", "Operaci�n Denegada.");
      put("201", "Operaci�n Denegada. Tarjeta Vencida.");
      put("202", "Operaci�n Denegada. Contactar con emisor.");
      put("204", "Operaci�n Denegada. Operaci�n no permitida para esta tarjeta.");
      put("206", "Operaci�n Denegada. Exceso de intentos de ingreso de PIN.");
      put("207", "Operaci�n Denegada. Contactar con emisor.");
      put("208", "Operaci�n Denegada. Tarjeta Perdida.");
      put("209", "Operaci�n Denegada. Tarjeta Robada.");
      put("263", "Operaci�n Denegada. Error en el env�o de par�metros.");
      put("264", "Operaci�n Denegada. Banco Emisor no esta disponible para realizar la autenticaci�n.");
      put("265", "Operaci�n Denegada. Password de tarjeta habiente incorrecto.");
      put("266", "Operaci�n Denegada. Tarjeta Vencida.");
      put("280", "Operaci�n Denegada. Clave err�nea.");
      put("290", "Operaci�n Denegada. Contactar con emisor.");
      put("300", "Operaci�n Denegada. N�mero de pedido del comercio duplicado. Favor no atender.");
      put("306", "Operaci�n Denegada. Contactar con emisor.");
      put("401", "Operaci�n Denegada. Tienda inhabilitada.");
      put("402", "Operaci�n Denegada. Tienda con rango de IP no valido.");
      put("403", "Operaci�n Denegada. Tarjeta no autenticada.");
      put("404", "Operaci�n Denegada. El monto de la transacci�n supera el valor m�ximo permitido.");
      put("405", "Operaci�n Denegada. La tarjeta ha superado la cantidad m�xima de transacciones en el d�a.");
      put("406", "Operaci�n Denegada. La tienda ha superado la cantidad m�xima de transacciones en el d�a.");
      put("407", "Operaci�n Denegada. El monto de la transacci�n no llega al m�nimo permitido.");
      put("408", "Operaci�n Denegada. CVV2 no coincide.");
      put("409", "Operaci�n Denegada. CVV2 no procesado por Banco.");
      put("410", "Operaci�n Denegada. CVV2 no procesado por no ingresado.");
      put("411", "Operaci�n Denegada. CVV2 no procesado por Banco.");
      put("412", "Operaci�n Denegada. CVV2 no reconocido por Banco.");
      put("413", "Operaci�n Denegada. Contactar con emisor.");
      put("414", "Operaci�n Denegada.");
      put("415", "Operaci�n Denegada.");
      put("416", "Operaci�n Denegada.");
      put("417", "Operaci�n Denegada.");
      put("418", "Operaci�n Denegada.");
      put("419", "Operaci�n Denegada.");
      put("420", "Operaci�n Denegada. Tarjeta no es VISA.");
      put("421", "Operaci�n Denegada. Contactar con emisor.");
      put("422", "Operaci�n Denegada. El comercio no est� configurado para usar este medio de pago.");
      put("423", "Operaci�n Denegada. Se cancel� el proceso de pago / Cancelled checkout.");
      put("424", "Operaci�n Denegada. Contactar con emisor.");
      put("425", "Operaci�n Denegada. Pa�s emisor incorrecto.");
      put("666", "Operaci�n Denegada. Problemas de comunicaci�n. Intente mas tarde.");
      put("807", "Operaci�n Denegada. Contactar con emisor.");
      put("900", "Operaci�n Denegada. Contactar con emisor.");
      put("904", "Operaci�n Denegada. Formato de mensaje err�neo.");
      put("909", "Operaci�n Denegada. Error de sistema.");
      put("910", "Operaci�n Denegada. Error de sistema.");
      put("912", "Operaci�n Denegada. Emisor no disponible.");
      put("913", "Operaci�n Denegada. Transmisi�n duplicada.");
      put("916", "Operaci�n Denegada. Contactar con emisor.");
      put("928", "Operaci�n Denegada. Contactar con emisor.");
      put("940", "Operaci�n Denegada. Transacci�n anulada previamente.");
      put("941", "Operaci�n Denegada. Transacci�n ya anulada previamente.");
      put("942", "Operaci�n Denegada.");
      put("943", "Operaci�n Denegada. Datos originales distintos.");
      put("945", "Operaci�n Denegada. Referencia repetida.");
      put("946", "Operaci�n Denegada. Operaci�n de anulaci�n en proceso.");
      put("947", "Operaci�n Denegada. Comunicaci�n duplicada.");
      put("948", "Operaci�n Denegada. Contactar con emisor.");
      put("949", "Operaci�n Denegada. Contactar con emisor.");
      put("965", "Operaci�n Denegada. Contactar con emisor.");
    }
  };

}
