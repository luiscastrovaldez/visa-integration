package com.visa.services;

import org.apache.log4j.Logger;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.stereotype.Service;

import com.visa.domain.RespuestaETicket;
import com.visa.domain.RespuestaVisa;

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
			LOGGER.error("", e);
		}
		return respuestaVisa;
	}

	public RespuestaETicket parseVisaETicketResponseXml(final String xml) {
		final Serializer serializer = new Persister();
		RespuestaETicket respuestaETicket = null;
		try {
			respuestaETicket = serializer.read(RespuestaETicket.class, xml);
		} catch (Exception e) {
			LOGGER.error("", e);
		}
		return respuestaETicket;
	}

}
