package com.visa.services;

import com.visa.xml.domain.ConsultaETicket;
import com.visa.xml.domain.RespuestaETicket;
import com.visa.xml.domain.RespuestaVisa;

public interface VisaXmlParserService {

	RespuestaVisa parseVisaOperationResponseXml(String xml);

	RespuestaETicket parseVisaETicketResponseXml(String xml);

	String parseVisaOperationResultRequestToXml(ConsultaETicket consultaETicket);

}
