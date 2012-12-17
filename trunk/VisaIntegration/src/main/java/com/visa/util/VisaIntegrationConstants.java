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

	public static final String MSG_ERROR_GENERICO = "Ha ocurrido un error, por favor intente más tarde.";
	public static final String MSG_ERROR_WEBSERVICE_VISA = "Ha ocurrido un error en la comunicación con Visa, por favor intente más tarde.";
	public static final String CAMPO_XML_SERVICE = "xmlService";

	/*
	 * Pagina Master
	 */
	public static final String APP_TITULO_VENTANA = "..:: Universidad Norbert Wiener - Pago electrónico ::..";
	public static final String APP_TITULO = "Sistema de Pago Electrónico";
	public static final String LOGIN_CERRAR_SESION = "[ Cerrar sesión ]";
	public static final String SEPARADOR_LINKS_CABECERA = "   |   ";
	public static final String IMPRIMIR_TEXTO = "[Imprimir]";
	public static final String VISA_TEXTO = "Esta tienda está autorizada por Visa para realizar transacciones electrónicas.";

	/*
	 * Pagina Login
	 */
	public static final String TIPO_USUARIO_ALUMNO = "0";
	public static final String TIPO_USUARIO_POSTULANTE = "1";
	public static final String TIPO_USUARIO_PROSPECTO = "2";

	public static final String LOGIN_USUARIO_ALUMNO = "Nombre de Usuario:";
	public static final String LOGIN_USUARIO_POSTULANTE = "Código de Postulante:";
	public static final String LOGIN_USUARIO_PROSPECTO = "Email:";

	public static final String LOGIN_CLAVE_ALUMNO = "Constraseña:";
	public static final String LOGIN_CLAVE_POSTULANTE = "Nro. Identificación:";
	public static final String LOGIN_CLAVE_PROSPECTO = "Nro. Identificación:";

	public static final String LOGIN_TEXTO = "Iniciar sesión para pagos electrónicos";
	public static final String LOGIN_INICIAR_SESION = " Iniciar Sesión ";
	public static final String INSTITUCION_TEXTO = "Institución:";

	/*
	 * Pagina Pagos
	 */
	public static final String PAGINA_PAGOS_TITULO = "Realizar pago electrónico VISA";
	public static final String CODIGO_USUARIO_TEXTO = "Código de ";
	public static final String USUARIO_ALUMNO = "Alumno:";
	public static final String USUARIO_POSTULANTE = "Postulante:";
	public static final String USUARIO_PROSPECTO = "Prospecto:";
	public static final String MENSAJE_ERROR_PERIODO = "El periodo académico debe ser el mismo por cada operación";
	public static final String CLAVE_USUARIO_SESION = "USUARIO_SESION";
	public static final String CLAVE_CARRERA_SESION = "CARRERA_SESION";
	public static final String CLAVE_TIPO_USUARIO_SESION = "TIPO_USUARIO_SESION";
	public static final String CODIGO_SERVICIO_TEXTO = "Código Servicio";
	public static final String DESCRIPCION_TEXTO = "Descripción";
	public static final String PERIODO_ACADEMICO_TEXTO = "Periodo Académico";
	public static final String CONSIDERACION_TEXTO = "Considere que las cuotas deben ser del mismo periodo académico.";

	/*
	 * Pagina Error
	 */
	public static final String CLAVE_MENSAJE_SESION = "MENSAJE";

	/*
	 * Pagina Respuesta de Visa
	 */
	public static final String CLAVE_RESPUESTA_SESION = "VISA_RESPUESTA";
	public static final String CLAVE_RESPUESTA_ERROR_SESION = "VISA_RESPUESTA_ERROR";
	public static final String PAGINA_REPUESTA_TITULO = "Detalle de la Operación de Pago electrónico:";
	public static final String NUMERO_PEDIDO_TEXTO = "Número Pedido:";
	public static final String IMPORTE_TRANSACCION_TEXTO = "Importe de la transacción:";
	public static final String DESCRIPCION_PRODUCTO_TEXTO = "Descripción del producto:";
	public static final String CODIGO_COMPRADOR_TEXTO = "Código institucional del comprador:";
	public static final String DESCRIPCION_CODIGO_TEXTO = "Descripción del código de acción:";
	public static final String POLITICAS_DEVOLUCION_TEXTO = "Políticas de Devolución:";
	public static final String TERMINOS_CONDICIONES_TEXTO = "Términos y Condiciones:";
	public static final String ERROR_RESPUESTA_VISA = "No se ha podido procesar la solicitud de pago electrónico. Revise los datos de su tarjeta.";
	public static final String ERROR_PROCESO_SOLICITUD = "No se ha podido procesar la solicitud de pago electrónico.";
	public static final String CORRECTO_PROCESO_SOLICITUD = "El pago electrónico se ha efectuado de forma satisfactoria.";
	public static final String ACEPTAR_TERMINOS_CONDICIONES = "Acepto Términos y Condiciones";

	 /*
   * Codigos de Accion
   */
  public static final Map<String, String> CODIGOSACCIONMAP = new HashMap<String, String>() {

    {
      put("101", "Operación Denegada. Tarjeta Vencida.");
      put("102", "Operación Denegada. Contactar con emisor.");
      put("104", "Operación Denegada. Operacion no permitida para esta tarjeta.");
      put("106", "Operación Denegada. Intentos de pin excedidos.");
      put("107", "Operación Denegada. Contactar con el emisor.");
      put("108", "Operación Denegada. Exceso de actividad.");
      put("109", "Operación Denegada. Identificación inválida de establecimiento.");
      put("110", "Operación Denegada. Operacion no permitida para esta tarjeta.");
      put("111", "Operación Denegada. Operación Denegada. El monto de la transacción supera el valor máximo permitido para operaciones virtuales.");
      put("112", "Operación Denegada. Se requiere clave.");
      put("116", "Operación Denegada. Fondos insuficientes.");
      put("117", "Operación Denegada. Clave incorrecta.");
      put("118", "Operación Denegada. Tarjeta Inválida.");
      put("119", "Operación Denegada. Exceso de intentos de ingreso de PIN.");
      put("121", "Operación Denegada.");
      put("126", "Operación Denegada. PIN inválido.");
      put("129", "Operación Denegada. Tarjeta no operativa.");
      put("180", "Operación Denegada. Tarjeta inválida.");
      put("181", "Operación Denegada Tarjeta con restricciones de Débito.");
      put("182", "Operación Denegada. Tarjeta con restricciones de Crédito.");
      put("183", "Operación Denegada. Error de sistema.");
      put("190", "Operación Denegada. Contactar con emisor.");
      put("191", "Operación Denegada. Contactar con emisor.");
      put("192", "Operación Denegada. Contactar con emisor.");
      put("199", "Operación Denegada.");
      put("201", "Operación Denegada. Tarjeta Vencida.");
      put("202", "Operación Denegada. Contactar con emisor.");
      put("204", "Operación Denegada. Operación no permitida para esta tarjeta.");
      put("206", "Operación Denegada. Exceso de intentos de ingreso de PIN.");
      put("207", "Operación Denegada. Contactar con emisor.");
      put("208", "Operación Denegada. Tarjeta Perdida.");
      put("209", "Operación Denegada. Tarjeta Robada.");
      put("263", "Operación Denegada. Error en el envío de parámetros.");
      put("264", "Operación Denegada. Banco Emisor no esta disponible para realizar la autenticación.");
      put("265", "Operación Denegada. Password de tarjeta habiente incorrecto.");
      put("266", "Operación Denegada. Tarjeta Vencida.");
      put("280", "Operación Denegada. Clave errónea.");
      put("290", "Operación Denegada. Contactar con emisor.");
      put("300", "Operación Denegada. Número de pedido del comercio duplicado. Favor no atender.");
      put("306", "Operación Denegada. Contactar con emisor.");
      put("401", "Operación Denegada. Tienda inhabilitada.");
      put("402", "Operación Denegada. Tienda con rango de IP no valido.");
      put("403", "Operación Denegada. Tarjeta no autenticada.");
      put("404", "Operación Denegada. El monto de la transacción supera el valor máximo permitido.");
      put("405", "Operación Denegada. La tarjeta ha superado la cantidad máxima de transacciones en el día.");
      put("406", "Operación Denegada. La tienda ha superado la cantidad máxima de transacciones en el día.");
      put("407", "Operación Denegada. El monto de la transacción no llega al mínimo permitido.");
      put("408", "Operación Denegada. CVV2 no coincide.");
      put("409", "Operación Denegada. CVV2 no procesado por Banco.");
      put("410", "Operación Denegada. CVV2 no procesado por no ingresado.");
      put("411", "Operación Denegada. CVV2 no procesado por Banco.");
      put("412", "Operación Denegada. CVV2 no reconocido por Banco.");
      put("413", "Operación Denegada. Contactar con emisor.");
      put("414", "Operación Denegada.");
      put("415", "Operación Denegada.");
      put("416", "Operación Denegada.");
      put("417", "Operación Denegada.");
      put("418", "Operación Denegada.");
      put("419", "Operación Denegada.");
      put("420", "Operación Denegada. Tarjeta no es VISA.");
      put("421", "Operación Denegada. Contactar con emisor.");
      put("422", "Operación Denegada. El comercio no está configurado para usar este medio de pago.");
      put("423", "Operación Denegada. Se canceló el proceso de pago / Cancelled checkout.");
      put("424", "Operación Denegada. Contactar con emisor.");
      put("425", "Operación Denegada. País emisor incorrecto.");
      put("666", "Operación Denegada. Problemas de comunicación. Intente mas tarde.");
      put("807", "Operación Denegada. Contactar con emisor.");
      put("900", "Operación Denegada. Contactar con emisor.");
      put("904", "Operación Denegada. Formato de mensaje erróneo.");
      put("909", "Operación Denegada. Error de sistema.");
      put("910", "Operación Denegada. Error de sistema.");
      put("912", "Operación Denegada. Emisor no disponible.");
      put("913", "Operación Denegada. Transmisión duplicada.");
      put("916", "Operación Denegada. Contactar con emisor.");
      put("928", "Operación Denegada. Contactar con emisor.");
      put("940", "Operación Denegada. Transacción anulada previamente.");
      put("941", "Operación Denegada. Transacción ya anulada previamente.");
      put("942", "Operación Denegada.");
      put("943", "Operación Denegada. Datos originales distintos.");
      put("945", "Operación Denegada. Referencia repetida.");
      put("946", "Operación Denegada. Operación de anulación en proceso.");
      put("947", "Operación Denegada. Comunicación duplicada.");
      put("948", "Operación Denegada. Contactar con emisor.");
      put("949", "Operación Denegada. Contactar con emisor.");
      put("965", "Operación Denegada. Contactar con emisor.");
    }
  };

}
