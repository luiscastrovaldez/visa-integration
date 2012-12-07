package com.visa.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.visa.util.VisaIntegrationConstants;

@ManagedBean
@ApplicationScoped
public class CommonBean {

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

}
