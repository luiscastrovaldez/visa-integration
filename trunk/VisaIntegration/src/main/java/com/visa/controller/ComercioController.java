package com.visa.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.faces.bean.ManagedProperty;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.visa.bo.beans.UserManagedBean;
import com.visa.util.VisaIntegrationConstants;
import com.visa.util.VisaIntegrationUtil;
import com.visa.webservice.consulteticket.WSConsultaEticketSoapProxy;
import com.visa.webservice.createeticket.WSEticketSoapProxy;
import com.visa.xml.domain.Campo;
import com.visa.xml.domain.ConsultaETicket;
import com.visa.xml.domain.Parametro;
import com.visa.xml.domain.RespuestaETicket;
import com.visa.xml.domain.RespuestaVisa;
import com.visa.xml.services.VisaXmlParserService;

@Controller
public class ComercioController {

	
	@ManagedProperty(value="#{userManagedBean}")
    private UserManagedBean userManagedBean; 
	
	@Autowired
	private VisaXmlParserService visaXmlParserService;

	@Value("#{props.codigoTienda}")
	public void setCodigoTienda(String codigoTienda) {
		this.codigoTienda = codigoTienda;
	}

	public String getCodigoTienda() {
		return codigoTienda;
	}

	private static final Logger LOGGER = Logger.getLogger(ComercioController.class);

	private String codigoTienda;

	
	@RequestMapping(value = "/createETicket", method = RequestMethod.POST)
	public ModelAndView createETicket(final @RequestBody String xmlService) throws Exception {
		LOGGER.info("Visa create eTicket");
		LOGGER.info(xmlService);
		
		LOGGER.info(" user name = " + userManagedBean.getFirstname());
		LOGGER.info(" user password = " + userManagedBean.getPassword());
		
		String xmlServiceStr = VisaIntegrationUtil.getParameterValue(xmlService, VisaIntegrationConstants.CAMPO_XML_SERVICE);
		if (xmlServiceStr == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO);
		}
		xmlServiceStr = VisaIntegrationUtil.decodeUrl(xmlServiceStr);
		LOGGER.info("Request Message");
		LOGGER.info(xmlServiceStr);
		final WSEticketSoapProxy sampleWSEticketSoapProxyid = new WSEticketSoapProxy();
		final String generaEticketXml = sampleWSEticketSoapProxyid.generaEticket(xmlServiceStr);
		LOGGER.info("Response Message");
		LOGGER.info(generaEticketXml);

		final RespuestaETicket eTicket = visaXmlParserService.parseVisaETicketResponseXml(generaEticketXml);

		if (eTicket == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO);
		}
		if (eTicket.getMensajes() != null && eTicket.getMensajes().size() > 0) {
			return showErrorPage(eTicket.getMensajes().get(0).getValue());
		}
		final ModelAndView mav = new ModelAndView("formularioSubmit");
		for (Iterator<Campo> iterator = eTicket.getRegistro().iterator(); iterator.hasNext();) {
			final Campo campo = (Campo) iterator.next();
			if (VisaIntegrationConstants.CAMPO_E_TICKET.equals(campo.getId())) {
				mav.addObject("eTicket", campo.getValue());
			}
		}
		return mav;
	}

	
	@RequestMapping(value = "/visaTestResponse", method = RequestMethod.POST)
	public ModelAndView visaTestResponse(final @RequestBody String xmlResponse) throws Exception {
		LOGGER.info("Visa Message");
		LOGGER.info(xmlResponse);

		final RespuestaVisa respuestaVisa = visaXmlParserService.parseVisaOperationResponseXml(xmlResponse);
		if (respuestaVisa == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO);
		}
		if (respuestaVisa.getMensajes() != null && respuestaVisa.getMensajes().size() > 0) {
			return showErrorPage(respuestaVisa.getMensajes().get(0).getValue());
		}
		final ModelAndView mav = new ModelAndView("visaResponse");
		for (Iterator<Campo> iterator = respuestaVisa.getPedido().getOperacion().getCampos().iterator(); iterator
				.hasNext();) {
			final Campo campo = (Campo) iterator.next();
			if (VisaIntegrationConstants.CAMPO_ESTADO.equals(campo.getId())) {
				mav.addObject("estado", campo.getValue());
			} else if (VisaIntegrationConstants.CAMPO_RESPUESTA.equals(campo.getId())) {
				mav.addObject("respuesta", campo.getValue());
			}
		}
		return mav;
	}

	
	@RequestMapping(value = "/visaResponse", method = RequestMethod.POST)
	public ModelAndView visaResponse(@RequestBody final String parameterList) throws Exception {
		LOGGER.info("Visa Post eTicket");
		LOGGER.info(parameterList);
		final String eTicket = VisaIntegrationUtil.getParameterValue(parameterList, VisaIntegrationConstants.CAMPO_E_TICKET);
		if (eTicket == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO);
		}
		final ConsultaETicket consultaETicket = new ConsultaETicket();
		final ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		Parametro parametro = new Parametro();
		parametro.setId(VisaIntegrationConstants.CAMPO_COD_TIENDA);
		parametro.setValue(getCodigoTienda());
		parametros.add(parametro);
		parametro = new Parametro();
		parametro.setId(VisaIntegrationConstants.CAMPO_E_TICKET);
		parametro.setValue(eTicket);
		parametros.add(parametro);
		consultaETicket.setParametros(parametros);

		final String requestXml = visaXmlParserService.parseVisaOperationResultRequestToXml(consultaETicket);
		if (requestXml == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO);
		}

		LOGGER.info("Request Message");
		LOGGER.info(requestXml);
		final WSConsultaEticketSoapProxy sampleWSConsultaEticketSoapProxyid = new WSConsultaEticketSoapProxy();
		final String consultaEticketXml = sampleWSConsultaEticketSoapProxyid.consultaEticket(requestXml);
		LOGGER.info("Response Message");
		LOGGER.info(consultaEticketXml);

		final RespuestaVisa respuestaVisa = visaXmlParserService
				.parseVisaOperationResponseXml(consultaEticketXml);
		if (respuestaVisa == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO);
		}
		if (respuestaVisa.getMensajes() != null && respuestaVisa.getMensajes().size() > 0) {
			return showErrorPage(respuestaVisa.getMensajes().get(0).getValue());
		}
		final ModelAndView mav = new ModelAndView("visaResponse");
		for (Iterator<Campo> iterator = respuestaVisa.getPedido().getOperacion().getCampos().iterator(); iterator
				.hasNext();) {
			final Campo campo = (Campo) iterator.next();
			if (VisaIntegrationConstants.CAMPO_ESTADO.equals(campo.getId())) {
				mav.addObject("estado", campo.getValue());
			} else if (VisaIntegrationConstants.CAMPO_RESPUESTA.equals(campo.getId())) {
				mav.addObject("respuesta", campo.getValue());
			}
		}
		return mav;
	}

	private ModelAndView showErrorPage(final String mensaje) {
		final ModelAndView mav = new ModelAndView("error");
		mav.addObject("message", mensaje);
		return mav;
	}

}