package com.visa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.visa.util.VisaIntegrationConstants;
import com.visa.xml.domain.ConsultaETicket;
import com.visa.xml.domain.Parametro;
import com.visa.xml.domain.RespuestaETicket;
import com.visa.xml.domain.RespuestaVisa;
import com.visa.xml.services.VisaXmlParserServiceImpl;

public class VisaXmlParserServiceImplTest {

	private VisaXmlParserServiceImpl visaXmlParserServiceImplTest;

	@Before
	public void setUp() {
		visaXmlParserServiceImplTest = new VisaXmlParserServiceImpl();
	}

	@Test
	public void testParseVisaOperationResponseXml() {
		final String xmlResponse = "<?xml version='1.0' encoding='UTF-8' ?><respuesta_eticket><pedido id = '699' eticket='2000601061101112270844150156'>"
				+ "<operacion id='1'><campo id='respuesta'>1</campo>"
				+ "<campo id='estado'>AUTORIZADO</campo>"
				+ "<campo id='cod_tienda'>998877601</campo>"
				+ "<campo id='nordent'>699</campo>"
				+ "<campo id='cod_accion'>000</campo>"
				+ "</operacion></pedido><mensajes><mensaje id='1'>Número de pedido ya existe</mensaje></mensajes></respuesta_eticket>";

		final RespuestaVisa respuestaVisa = visaXmlParserServiceImplTest
				.parseVisaOperationResponseXml(xmlResponse);
		assertNotNull(respuestaVisa);
		assertEquals("699", respuestaVisa.getPedido().getId());
		assertEquals("2000601061101112270844150156", respuestaVisa.getPedido().getEticket());
		assertEquals(1, respuestaVisa.getPedido().getOperacion().getId());
		assertEquals(5, respuestaVisa.getPedido().getOperacion().getCampos().size());
		assertEquals(1, respuestaVisa.getMensajes().size());
		assertEquals("1", respuestaVisa.getMensajes().get(0).getId());
	}

	@Test
	public void testParseVisaETicketResponseXml() {
		final String xmlResponse = "<?xml version='1.0' encoding='UTF-8' ?><eticket><registro>"
				+ "<campo id='CODTIENDA'>998877601</campo>"
				+ "<campo id='NUMORDEN'>699</campo> "
				+ "<campo id='ETICKET'>2000601061101112270844150156</campo>"
				+ "</registro><mensajes><mensaje id='1'>Número de pedido ya existe</mensaje></mensajes></eticket>";

		final RespuestaETicket respuestaVisa = visaXmlParserServiceImplTest
				.parseVisaETicketResponseXml(xmlResponse);
		assertNotNull(respuestaVisa);
		assertEquals(3, respuestaVisa.getRegistro().size());
		assertEquals(VisaIntegrationConstants.CAMPO_COD_TIENDA, respuestaVisa.getRegistro().get(0).getId());
		assertEquals(VisaIntegrationConstants.CAMPO_NUM_ORDEN, respuestaVisa.getRegistro().get(1).getId());
		assertEquals(VisaIntegrationConstants.CAMPO_E_TICKET, respuestaVisa.getRegistro().get(2).getId());
		assertEquals("998877601", respuestaVisa.getRegistro().get(0).getValue());
		assertEquals("699", respuestaVisa.getRegistro().get(1).getValue());
		assertEquals("2000601061101112270844150156", respuestaVisa.getRegistro().get(2).getValue());
		assertEquals(1, respuestaVisa.getMensajes().size());
		assertEquals("1", respuestaVisa.getMensajes().get(0).getId());
	}

	@Test
	public void testParseVisaOperationResultRequestToXml() {
		final ConsultaETicket consultaETicket = new ConsultaETicket();
		final ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		final Parametro parametro = new Parametro();
		parametro.setId(VisaIntegrationConstants.CAMPO_E_TICKET);
		parametro.setValue("2000601061101112270844150156");
		parametros.add(parametro);
		consultaETicket.setParametros(parametros);

		final String xml = visaXmlParserServiceImplTest.parseVisaOperationResultRequestToXml(consultaETicket);
		assertNotNull(xml);
		System.out.println(xml);
	}

}
