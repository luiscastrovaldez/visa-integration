package com.visa.web.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.visa.util.VisaIntegrationConstants;

@ManagedBean
@ApplicationScoped
public class CommonBean {

	private static final Logger LOGGER = Logger.getLogger(CommonBean.class);

	public String getAppTitulo() {
		return VisaIntegrationConstants.APP_TITULO;
	}

	public String getAppTituloVentana() {
		return VisaIntegrationConstants.APP_TITULO_VENTANA;
	}

	public String getLoginIniciarSesion() {
		return VisaIntegrationConstants.LOGIN_INICIAR_SESION;
	}

	public String getLoginCerrarSesion() {
		return VisaIntegrationConstants.LOGIN_CERRAR_SESION;
	}

	public String getLoginTexto() {
		return VisaIntegrationConstants.LOGIN_TEXTO;
	}

	public String getImprimirTexto() {
		return VisaIntegrationConstants.IMPRIMIR_TEXTO;
	}

	public String getSeparadorLinksCabecera() {
		return VisaIntegrationConstants.SEPARADOR_LINKS_CABECERA;
	}

	public String getInstitucionTexto() {
		return VisaIntegrationConstants.INSTITUCION_TEXTO;
	}

	public String getPaginaPagosTitulo() {
		return VisaIntegrationConstants.PAGINA_PAGOS_TITULO;
	}

	public String getMensajeErrorPeriodo() {
		return VisaIntegrationConstants.MENSAJE_ERROR_PERIODO;
	}

	public String getErrorMessage() {
		final HttpSession session = getCurrentSession();
		if (session != null) {
			return (String)session.getAttribute(VisaIntegrationConstants.CLAVE_MENSAJE_SESION);
		}
		LOGGER.info("No existe la sesion");
		return VisaIntegrationConstants.MSG_ERROR_GENERICO;
	}

	public String getEticket() {
		final HttpSession session = getCurrentSession();
		if (session != null) {
			final String eTicket = (String)session.getAttribute(VisaIntegrationConstants.CAMPO_E_TICKET);
			LOGGER.info(VisaIntegrationConstants.CAMPO_E_TICKET + ": " + eTicket);
			return eTicket;
		}
		LOGGER.info("No existe la sesion");
		return null;
	}

	private HttpSession getCurrentSession() {
		final FacesContext context = FacesContext.getCurrentInstance();
		return (HttpSession) context.getExternalContext().getSession(false);
	}

}
