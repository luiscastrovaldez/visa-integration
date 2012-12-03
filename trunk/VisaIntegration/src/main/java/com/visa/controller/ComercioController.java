package com.visa.controller;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.multimerchantvisanet.www.solicitudtransaccion.WSEticketSoapProxy;
import com.visa.domain.Campo;
import com.visa.domain.RespuestaETicket;
import com.visa.domain.RespuestaVisa;
import com.visa.services.VisaXmlParserService;
import com.visa.util.VisaIntegrationConstants;

@Controller
public class ComercioController {

	@Autowired
	private VisaXmlParserService visaXmlParserService;

	private static final Logger LOGGER = Logger
			.getLogger(ComercioController.class);

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/createETicket", method = RequestMethod.POST)
	public ModelAndView createETicket(final @RequestBody String xmlService)
			throws Exception {
		LOGGER.info("Request Message");
		LOGGER.info(xmlService);
		final WSEticketSoapProxy sampleWSEticketSoapProxyid = new WSEticketSoapProxy();
		final String generaEticketXml = sampleWSEticketSoapProxyid
				.generaEticket(xmlService);
		LOGGER.info("Response Message");
		LOGGER.info(generaEticketXml);

		final RespuestaETicket eTicket = visaXmlParserService
				.parseVisaETicketResponseXml(generaEticketXml);

		if (eTicket.getMensajes() != null && eTicket.getMensajes().size() > 0) {
			final ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", eTicket.getMensajes().get(0).getValue());
			return mav;
		}
		final ModelAndView mav = new ModelAndView("formularioSubmit");
		for (Iterator iterator = eTicket.getRegistro().iterator(); iterator
				.hasNext();) {
			final Campo campo = (Campo) iterator.next();
			if (VisaIntegrationConstants.CAMPO_E_TICKET.equals(campo.getId())) {
				mav.addObject("eTicket", campo.getValue());
			}
		}
		return mav;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/visaResponse", method = RequestMethod.POST)
	public ModelAndView visaResponse(final @RequestBody String xmlResponse)
			throws Exception {
		LOGGER.info("Visa Message");
		LOGGER.info(xmlResponse);

		final RespuestaVisa respuestaVisa = visaXmlParserService
				.parseVisaOperationResponseXml(xmlResponse);
		if (respuestaVisa.getMensajes() != null
				&& respuestaVisa.getMensajes().size() > 0) {
			final ModelAndView mav = new ModelAndView("error");
			mav.addObject("message", respuestaVisa.getMensajes().get(0)
					.getValue());
			return mav;
		}
		final ModelAndView mav = new ModelAndView("visaResponse");
		for (Iterator iterator = respuestaVisa.getPedido().getOperacion()
				.getCampos().iterator(); iterator.hasNext();) {
			final Campo campo = (Campo) iterator.next();
			if (VisaIntegrationConstants.CAMPO_ESTADO.equals(campo.getId())) {
				mav.addObject("estado", campo.getValue());
			} else if (VisaIntegrationConstants.CAMPO_RESPUESTA.equals(campo
					.getId())) {
				mav.addObject("respuesta", campo.getValue());
			}
		}
		return mav;
	}

}
