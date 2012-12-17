package com.visa.util;

import java.util.HashMap;
import java.util.Map;

public class CodigosAccionUtil {

	public static final Map<String, String> MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("101", "Operación Denegada. Tarjeta Vencida.");
			put("102", "Operación Denegada. Contactar con emisor.");
			put("104", "Operación Denegada. Operacion no permitida para esta tarjeta.");
			put("106", "Operación Denegada. Intentos de pin excedidos.");
			put("107", "Operación Denegada. Contactar con el emisor.");
			put("108", "Operación Denegada. Exceso de actividad.");
			put("109", "Operación Denegada. Identificación inválida de establecimiento.");
			put("110", "Operación Denegada. Operacion no permitida para esta tarjeta.");
			put("111",
					"Operación Denegada. Operación Denegada. El monto de la transacción supera el valor máximo permitido para operaciones virtuales.");
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
