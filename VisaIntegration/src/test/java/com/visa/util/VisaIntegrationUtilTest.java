package com.visa.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class VisaIntegrationUtilTest {

	@Test
	public void testGetParameterValue() {
		String parameterStr = "test=value&test2=value2";
		assertEquals("value", VisaIntegrationUtil.getParameterValue(parameterStr, "test"));

		parameterStr = "test3=value&test2=value2";
		assertNull(VisaIntegrationUtil.getParameterValue(parameterStr, "test"));

		parameterStr = "test2=value&test=value2";
		assertEquals("value2", VisaIntegrationUtil.getParameterValue(parameterStr, "test"));

		parameterStr = "test2=valuevalue&test=value&test3=valuevaluevalue";
		assertEquals("value", VisaIntegrationUtil.getParameterValue(parameterStr, "test"));
	}

	@Test
	public void testDecodeUrl() {
		final String url = "%3C%3Fxml+version%3D%271.0%27+encoding%3D%27UTF-8%27+%3F%3E%3Cnuevo_eticket%3E%3Cparametros%3E%3Cparametro+id%3D%27CANAL%27%3E3%3C%2Fparametro%3E%3Cparametro+id%3D%27PRODUCTO%27%3E1%3C%2Fparametro%3E%3Cparametro+id%3D%27CODTIENDA%27%3E466246301%3C%2Fparametro%3E%3Cparametro+id%3D%27NUMORDEN%27%3E2%3C%2Fparametro%3E%3Cparametro+id%3D%27MOUNT%27%3E100.00%3C%2Fparametro%3E%3Cparametro+id%3D%27DATO_COMERCIO%27%3EXXXX%3C%2Fparametro%3E%3C%2Fparametros%3E%3C%2Fnuevo_eticket%3E";
		final String result = VisaIntegrationUtil.decodeUrl(url);
		assertEquals(
				"<?xml version='1.0' encoding='UTF-8' ?><nuevo_eticket><parametros><parametro id='CANAL'>3</parametro><parametro id='PRODUCTO'>1</parametro><parametro id='CODTIENDA'>466246301</parametro><parametro id='NUMORDEN'>2</parametro><parametro id='MOUNT'>100.00</parametro><parametro id='DATO_COMERCIO'>XXXX</parametro></parametros></nuevo_eticket>",
				result);

		assertEquals(
				"<?xml version='1.0' encoding='UTF-8' ?><nuevo_eticket></nuevo_eticket>",
				VisaIntegrationUtil.decodeUrl("<?xml version='1.0' encoding='UTF-8' ?><nuevo_eticket></nuevo_eticket>"));
	}

}
