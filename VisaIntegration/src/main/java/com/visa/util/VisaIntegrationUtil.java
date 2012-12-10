package com.visa.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Locale;

public class VisaIntegrationUtil {

	public static String getParameterValue(final String parameterStr, final String parameterId) {
		final int pos = parameterStr.indexOf(parameterId + "=");
		if (pos != -1) {
			String parameterValue = parameterStr.substring(pos + parameterId.length() + 1, parameterStr
					.length());
			final int posFinal = parameterValue.indexOf("&") == -1 ? parameterValue.length() : parameterValue
					.indexOf("&");
			parameterValue = parameterValue.substring(0, posFinal);
			return parameterValue;
		} else {
			return null;
		}
	}

	public static String decodeUrl(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}
	
	public static String formatDoubleString(String numberStr) {
		double number;
		try {
			number = Double.valueOf(numberStr);
		} catch (Exception e) {
			number = 0;
		}
		return String.format(Locale.ENGLISH, "%1$,.2f", number);
	}
}
