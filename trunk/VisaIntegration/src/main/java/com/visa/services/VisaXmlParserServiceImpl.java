package com.visa.services;

import java.io.StringWriter;

import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Service;

import com.visa.xml.domain.ConsultaETicket;
import com.visa.xml.domain.RespuestaETicket;
import com.visa.xml.domain.RespuestaVisa;

@Service("visaXmlParserServiceImpl")
public class VisaXmlParserServiceImpl implements VisaXmlParserService {

	private static final Logger LOGGER = Logger
			.getLogger(VisaXmlParserServiceImpl.class);

	public RespuestaVisa parseVisaOperationResponseXml(final String xml) {
		final Serializer serializer = new Persister();
		RespuestaVisa respuestaVisa = null;
		try {
			respuestaVisa = serializer.read(RespuestaVisa.class, xml);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Error en parseo de XML", e);
		}
		return respuestaVisa;
	}

	public RespuestaETicket parseVisaETicketResponseXml(final String xml) {
		final Serializer serializer = new Persister();
		RespuestaETicket respuestaETicket = null;
		try {
			respuestaETicket = serializer.read(RespuestaETicket.class, xml);
		} catch (Exception e) {
			LOGGER.error("Error en parseo de XML", e);
		}
		return respuestaETicket;
	}

	public String parseVisaOperationResultRequestToXml(final ConsultaETicket consultaETicket) {
		final Serializer serializer = new Persister();
		try {
			final StringWriter writer = new StringWriter();
			serializer.write(consultaETicket, writer);
			return writer.toString();
		} catch (Exception e) {
			LOGGER.error("Error en parseo de XML", e);
		}
		return null;
	}

}
