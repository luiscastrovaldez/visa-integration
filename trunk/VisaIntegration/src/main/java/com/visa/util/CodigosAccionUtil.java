package com.visa.util;

import java.util.HashMap;
import java.util.Map;

public class CodigosAccionUtil {

	public static final Map<String, String> MAP = new HashMap<String, String>() {

		private static final long serialVersionUID = 1L;

		{
			put("101", "Operaci�n Denegada. Tarjeta Vencida.");
			put("102", "Operaci�n Denegada. Contactar con emisor.");
			put("104", "Operaci�n Denegada. Operacion no permitida para esta tarjeta.");
			put("106", "Operaci�n Denegada. Intentos de pin excedidos.");
			put("107", "Operaci�n Denegada. Contactar con el emisor.");
			put("108", "Operaci�n Denegada. Exceso de actividad.");
			put("109", "Operaci�n Denegada. Identificaci�n inv�lida de establecimiento.");
			put("110", "Operaci�n Denegada. Operacion no permitida para esta tarjeta.");
			put("111",
					"Operaci�n Denegada. Operaci�n Denegada. El monto de la transacci�n supera el valor m�ximo permitido para operaciones virtuales.");
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
