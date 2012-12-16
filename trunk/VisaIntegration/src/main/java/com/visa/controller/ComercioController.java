package com.visa.controller;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.visa.domain.TranVisaRespuesta;
import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;
import com.visa.util.VisaIntegrationUtil;
import com.visa.webservice.consulteticket.WSConsultaEticketSoapProxy;
import com.visa.webservice.createeticket.WSEticketSoapProxy;
import com.visa.xml.domain.Campo;
import com.visa.xml.domain.ConsultaETicket;
import com.visa.xml.domain.Operacion;
import com.visa.xml.domain.Parametro;
import com.visa.xml.domain.RespuestaETicket;
import com.visa.xml.domain.RespuestaVisa;
import com.visa.xml.services.VisaXmlParserService;

@Controller
public class ComercioController {
	
	@Autowired
	private VisaXmlParserService visaXmlParserService;

	@Autowired
	private VisaIntegration visaIntegration;

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
	public ModelAndView createETicket(final @RequestBody String xmlService, final HttpSession session) throws Exception {
		LOGGER.info("Visa create eTicket");
		LOGGER.info(xmlService);

		String xmlServiceStr = VisaIntegrationUtil.getParameterValue(xmlService, VisaIntegrationConstants.CAMPO_XML_SERVICE);
		if (xmlServiceStr == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO, session);
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
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO, session);
		}
		if (eTicket.getMensajes() != null && eTicket.getMensajes().size() > 0) {
			return showErrorPage(eTicket.getMensajes().get(0).getValue(), session);
		}
		final ModelAndView mav = new ModelAndView(getRedirect("formularioSubmit"));
		for (Iterator<Campo> iterator = eTicket.getRegistro().iterator(); iterator.hasNext();) {
			final Campo campo = (Campo) iterator.next();
			if (VisaIntegrationConstants.CAMPO_E_TICKET.equals(campo.getId())) {
				session.setAttribute(VisaIntegrationConstants.CAMPO_E_TICKET, campo.getValue());
			}
		}
		return mav;
	}

	@RequestMapping(value = "/visaResponse", method = RequestMethod.POST)
	public ModelAndView visaResponse(@RequestBody final String parameterList, final HttpSession session) throws Exception {
		LOGGER.info("Visa Post eTicket");
		LOGGER.info(parameterList);
		final String eTicket = VisaIntegrationUtil.getParameterValue(parameterList, VisaIntegrationConstants.CAMPO_E_TICKET);
		if (eTicket == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO, session);
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
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO, session);
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
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO, session);
		}
		if (respuestaVisa.getMensajes() != null && respuestaVisa.getMensajes().size() > 0) {
			return showErrorPage(respuestaVisa.getMensajes().get(0).getValue(), session);
		}
		final ModelAndView mav = new ModelAndView("visaResponse");
		if (!CollectionUtils.isEmpty(respuestaVisa.getPedido().getOperaciones())) {
			Operacion operacion = null;
			for (Operacion operacionX : respuestaVisa.getPedido().getOperaciones()) {
				if (operacionX.getId() == 1) {
					operacion = operacionX;
					break;
				}
			}
			if (operacion != null && !CollectionUtils.isEmpty(operacion.getCampos())) {
				final TranVisaRespuesta tranVisaRespuesta = new TranVisaRespuesta();
				for (Campo campo : operacion.getCampos()) {
					if (VisaIntegrationConstants.CAMPO_ESTADO.equals(campo.getId())) {
						tranVisaRespuesta.setEstado(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_RESPUESTA.equals(campo.getId())) {
						tranVisaRespuesta.setRespuesta(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_COD_TIENDA.equals(campo.getId())) {
						tranVisaRespuesta.setCodTienda(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_NORDENT.equals(campo.getId())) {
						tranVisaRespuesta.setnOrdenT(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_COD_ACCION.equals(campo.getId())) {
						tranVisaRespuesta.setCodAccion(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_PAN.equals(campo.getId())) {
						tranVisaRespuesta.setPan(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_NOMBRE_TH.equals(campo.getId())) {
						tranVisaRespuesta.setNombreTh(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_ORI_TARJETA.equals(campo.getId())) {
						tranVisaRespuesta.setOriTarjeta(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_NOM_EMISOR.equals(campo.getId())) {
						tranVisaRespuesta.setNomEmisor(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_ECI.equals(campo.getId())) {
						tranVisaRespuesta.setEci(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_DSC_ECI.equals(campo.getId())) {
						tranVisaRespuesta.setDscEci(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_COD_AUTORIZA.equals(campo.getId())) {
						tranVisaRespuesta.setCodAutoriza(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_COD_RESCVV2.equals(campo.getId())) {
						tranVisaRespuesta.setCodRescvv2(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_IMP_AUTORIZADO.equals(campo.getId())) {
						tranVisaRespuesta.setImpAutorizado(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_FECHAYHORA_TX.equals(campo.getId())) {
						tranVisaRespuesta.setFechaHoraTx(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_FECHAYHORA_DEPOSITO.equals(campo.getId())) {
						tranVisaRespuesta.setFechaHoraDeposito(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_FECHAYHORA_DEVOLUCION.equals(campo.getId())) {
						tranVisaRespuesta.setFechaHoraDevolucion(campo.getValue());
					} else if (VisaIntegrationConstants.CAMPO_DATO_COMERCIO.equals(campo.getId())) {
						tranVisaRespuesta.setDatoComercio(campo.getValue());
					}
				}
        final String usuario = (String) session.getAttribute(VisaIntegrationConstants.CLAVE_USUARIO_SESION);
        final String carrera = (String) session.getAttribute(VisaIntegrationConstants.CLAVE_CARRERA_SESION);
        tranVisaRespuesta.setAlumno(usuario);
        tranVisaRespuesta.setCarrera(carrera);
				// Registrar la Respuesta que envía VISA
        final String estado = tranVisaRespuesta.getRespuesta().equals("0") ? "D" : "A"; 
				visaIntegration.registraTranVisaRespuesta(tranVisaRespuesta, estado);
				session.setAttribute(VisaIntegrationConstants.CLAVE_RESPUESTA_SESION, tranVisaRespuesta);
			} else {
	session.setAttribute(VisaIntegrationConstants.CLAVE_RESPUESTA_ERROR_SESION, VisaIntegrationConstants.ERROR_RESPUESTA_VISA);
			}
		} else {
			session.setAttribute(VisaIntegrationConstants.CLAVE_RESPUESTA_ERROR_SESION, VisaIntegrationConstants.ERROR_RESPUESTA_VISA);
		}
		return mav;
	}

	private ModelAndView showErrorPage(final String mensaje, final HttpSession session) {
		final ModelAndView mav = new ModelAndView(getRedirect("error"));
		session.setAttribute(VisaIntegrationConstants.CLAVE_MENSAJE_SESION, mensaje);
		return mav;
	}

	private String getRedirect(String url) {
		return "redirect:" + url + ".visa";
	}
}
