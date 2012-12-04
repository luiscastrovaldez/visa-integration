package com.visa.services;

import com.visa.domain.ConsultaETicket;
import com.visa.domain.RespuestaETicket;
import com.visa.domain.RespuestaVisa;

public interface VisaXmlParserService {

	RespuestaVisa parseVisaOperationResponseXml(String xml);

	RespuestaETicket parseVisaETicketResponseXml(String xml);

	String parseVisaOperationResultRequestToXml(ConsultaETicket consultaETicket);

}
