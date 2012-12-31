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

import com.visa.beans.DatosCorreo;
import com.visa.domain.InfoTranVISA;
import com.visa.domain.NombreConcepto;
import com.visa.domain.TranVisaRespuesta;
import com.visa.domain.Usuario;
import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;
import com.visa.util.VisaIntegrationUtil;
import com.visa.util.services.EmailServices;
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

	@Autowired
	private EmailServices emailServices;

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
		String generaEticketXml = null;
		final WSEticketSoapProxy sampleWSEticketSoapProxyid = new WSEticketSoapProxy();
		try {
			generaEticketXml = sampleWSEticketSoapProxyid.generaEticket(xmlServiceStr);
		} catch (Exception ex) {
			LOGGER.error("Error en creacion de E-ticket", ex);
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_WEBSERVICE_VISA, session);
		}
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
		final String eTicket = VisaIntegrationUtil.getParameterValue(parameterList, VisaIntegrationConstants.CAMPO_E_TICKET_RESPUESTA);
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
		String consultaEticketXml = null;
		final WSConsultaEticketSoapProxy sampleWSConsultaEticketSoapProxyid = new WSConsultaEticketSoapProxy();
		try {
			consultaEticketXml = sampleWSConsultaEticketSoapProxyid.consultaEticket(requestXml);
		} catch (Exception ex) {
			LOGGER.error("Error en consulta E-ticket", ex);
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_WEBSERVICE_VISA, session);
		}
		LOGGER.info("Response Message");
		LOGGER.info(consultaEticketXml);

		final RespuestaVisa respuestaVisa = visaXmlParserService.parseVisaOperationResponseXml(consultaEticketXml);
		if (respuestaVisa == null) {
			return showErrorPage(VisaIntegrationConstants.MSG_ERROR_GENERICO, session);
		}
		if (respuestaVisa.getMensajes() != null && respuestaVisa.getMensajes().size() > 0) {
			return showErrorPage(respuestaVisa.getMensajes().get(0).getValue(), session);
		}
		final ModelAndView mav = new ModelAndView(getRedirect("visaResponse"));
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
		        final String tipoUsuario = (String) session.getAttribute(VisaIntegrationConstants.CLAVE_TIPO_USUARIO_SESION);
		        tranVisaRespuesta.setAlumno(usuario);
		        tranVisaRespuesta.setCarrera(carrera);
				// Registrar la Respuesta que envía VISA
		        final String estado = tranVisaRespuesta.getRespuesta().equals("1") ? "A" : "D"; 
				visaIntegration.registraTranVisaRespuesta(tranVisaRespuesta, estado);
				final int intNOrdenT = Integer.valueOf(tranVisaRespuesta.getnOrdenT());
				final InfoTranVISA infoTranVISA = visaIntegration.obtenerInformacionTransaccionVisa(intNOrdenT);
				tranVisaRespuesta.setFechaHoraTx(VisaIntegrationUtil.formatDate(infoTranVISA.getFechaTran()));
				tranVisaRespuesta.setImpAutorizado(VisaIntegrationUtil.formatBigDecimal(infoTranVISA.getMonto()));
				StringBuilder sb = new StringBuilder();
				for (NombreConcepto nombreConcepto : infoTranVISA.getListaConceptos()) {
					sb.append(nombreConcepto.getNombre());
					sb.append("<br/>");
				}
				tranVisaRespuesta.setDescripcionProducto(sb.toString());
				if (tranVisaRespuesta.getRespuesta().equals("1")) {
					//ACTUALIZAR LOS DATOS EN LAS TABLAS DE TESORERIAS
					visaIntegration.actualizarDatosVirtual(intNOrdenT, Integer.valueOf(tipoUsuario));
	                enviarEmailConfirmaPago(tipoUsuario, usuario, intNOrdenT, VisaIntegrationUtil.formatBigDecimal(infoTranVISA.getMonto()));

	                if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(tipoUsuario)) {
	                    enviarUsuarioClaveAlumno(usuario, carrera);
	                }
					mav.setViewName(getRedirect("visaResponse"));
					return mav;
				}
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

	private void enviarEmailConfirmaPago(final String tipoUsuario, final String comprador, int intNOrdenT, String monto) {
		LOGGER.info("enviarEmailConfirmaPago");
		String strInfo = null;
		String strConcepto = null;
		try {
			if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(tipoUsuario)) {
				strInfo = visaIntegration.obtenerNombreAlumnoPG(comprador);
				strConcepto = "Pago de Cuota a ";
			} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(tipoUsuario)) {
				strInfo = visaIntegration.obtenerNombrePostulante(comprador);
				strConcepto = "Pago de Matrícula y/o Cuota a ";
			} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(tipoUsuario)) {
				strInfo = visaIntegration.obtenerNombreProspecto(comprador);
				strConcepto = "Inscripción a ";
			}
			strInfo = strInfo.replace("|", "-");
			LOGGER.info(strInfo);
			final String[] datos = strInfo.split("-");
			if (datos.length > 1 && !datos[0].isEmpty() && !datos[1].isEmpty()) {
				final DatosCorreo datosCorreo = new DatosCorreo();
				datosCorreo.setNombre(datos[0]);
				datosCorreo.setConfirmacion(true);
				datosCorreo.setConcepto(strConcepto);
				datosCorreo.setIdCliente(comprador);
				datosCorreo.setIdTransferencia(Integer.toString(intNOrdenT));
				datosCorreo.setMonto(monto);
				datosCorreo.setAddressTo(datos[1]);
				datosCorreo.setAddressCc(VisaIntegrationConstants.CORREO_COPIA);
				emailServices.sendEmail(datosCorreo);
			}
		} catch (Exception ex) {
			LOGGER.error("Error al mandar el correo confirma pago", ex);
		}
	}

	private void enviarUsuarioClaveAlumno(final String idPostulante, final String carrera) {
		LOGGER.info("enviarUsuarioClaveAlumno");
		String strInfo;
		try {
			strInfo = visaIntegration.obtenerNombrePostulante(idPostulante);
			strInfo = strInfo.replace("|", "-");
			String[] datos = strInfo.split("-");
			if (datos.length > 1 && !datos[0].isEmpty() && !datos[1].isEmpty()) {
				final Usuario usuario = visaIntegration.obtenerDatosNuevoAlumno(idPostulante, carrera);
				final DatosCorreo datosCorreo = new DatosCorreo();
				datosCorreo.setNombre(datos[0]);
				datosCorreo.setNuevoAlumno(true);
				datosCorreo.setUsuario(usuario.getUsuario());
				datosCorreo.setClave(usuario.getClave());
				datosCorreo.setAddressTo(datos[1]);
				datosCorreo.setAddressCc(VisaIntegrationConstants.CORREO_COPIA);
				emailServices.sendEmail(datosCorreo);
			}
		} catch (Exception e) {
			LOGGER.error("Error al mandar el correo confirma pago", e);
		}
		
	}

}
