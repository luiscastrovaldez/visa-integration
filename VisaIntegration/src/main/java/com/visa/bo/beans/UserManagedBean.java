package com.visa.bo.beans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(UserManagedBean.class);

	@ManagedProperty(value = "#{visaIntegration}")
	private VisaIntegration visaIntegration;

	private String username;
	private String password;
	private int numAtencion;
	private String tipoUsuarioLogueado;

	public VisaIntegration getVisaIntegration() {
		return visaIntegration;
	}

	public void setVisaIntegration(VisaIntegration visaIntegration) {
		this.visaIntegration = visaIntegration;
	}

	public int getNumAtencion() {
		return numAtencion;
	}

	public void setNumAtencion(int numAtencion) {
		this.numAtencion = numAtencion;
	}

	public String getTipoUsuarioLogueado() {
		return tipoUsuarioLogueado;
	}

	public void setTipoUsuarioLogueado(String tipoUsuarioLogueado) {
		this.tipoUsuarioLogueado = tipoUsuarioLogueado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String cerrarSesion() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		if (session != null)session.invalidate();
		
		return "login?faces-redirect=true" ;
	}

	public String getUserNameLabelText() {
		final String strTipoUsuario = getTipoUsuario();
		if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(strTipoUsuario)) {
			return VisaIntegrationConstants.LOGIN_USUARIO_ALUMNO;
		} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(strTipoUsuario)) {
			return VisaIntegrationConstants.LOGIN_USUARIO_POSTULANTE;
		} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(strTipoUsuario)) {
			return VisaIntegrationConstants.LOGIN_USUARIO_PROSPECTO;
		}
		return null;
	}

	public String getPasswordLabelText() {
		final String strTipoUsuario = getTipoUsuario();
		if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(strTipoUsuario)) {
			return VisaIntegrationConstants.LOGIN_CLAVE_ALUMNO;
		} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(strTipoUsuario)) {
			return VisaIntegrationConstants.LOGIN_CLAVE_POSTULANTE;
		} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(strTipoUsuario)) {
			return VisaIntegrationConstants.LOGIN_CLAVE_PROSPECTO;
		}
		return null;
	}

	public boolean getUsuarioLogueado() {
		return getUsername() != null;
	}

	public String loginGeneral() throws Exception {

		FacesContext context = FacesContext.getCurrentInstance();
		String page = "";
		try {
			LOGGER.info("usuario " + getUsername());
			final String strTipoUsuario = getTipoUsuario();

			if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(strTipoUsuario)) {
				page = alumnoLogin(context);
			} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(strTipoUsuario)) {
				page = postulanteLogin(context);
			} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(strTipoUsuario)) {
				page = prospectoLogin(context);
			}
			return page;
		} catch (Exception e) {
			LOGGER.error("Error Login General", e);
			context = FacesContext.getCurrentInstance();
			context.addMessage("messaje", new FacesMessage("Ha ocurrido un error. Por favor, vuelva a intentarlo en unos minutos."));
			return "error?faces-redirect=true";
		}

	}

	private String getTipoUsuario() {
		final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getParameter("usr") == null ? VisaIntegrationConstants.TIPO_USUARIO_ALUMNO : request.getParameter("usr");		
	}

	private String postulanteLogin(FacesContext context) throws Exception {
		int intPostulante;
		intPostulante = this.visaIntegration.verificaPostulanteExiste(getUsername(), getPassword());
		if (intPostulante == 1) {
			setTipoUsuarioLogueado(VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE);
			return "pagos?faces-redirect=true";
		} else {
			context.addMessage("messaje", new FacesMessage(
					"Los datos ingresados no corresponden a un usuario registrado. Verifique y vuelva a intentarlo."));
		}
		return "login?faces-redirect=true";
	}

	private String prospectoLogin(FacesContext context) throws Exception {
		int intAtencion = 0;
		String [] strIdentificacion = getPassword().trim().split("-");
		if (strIdentificacion.length > 1) {
			try {
				intAtencion = Integer.valueOf(strIdentificacion[0]);
			} catch (Exception ex) {
				LOGGER.error("No se pudo convertir el num de atencion", ex);
			}	
		}
		int intProspecto = this.visaIntegration.verificaProspectoExiste(getUsername(), getPassword(), intAtencion);
		if (intProspecto == 1) {
			setTipoUsuarioLogueado(VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO);
			setNumAtencion(intAtencion);
			return "pago?faces-redirect=trues";
		} else {
			context.addMessage("messaje", new FacesMessage(
					"Los datos ingresados no corresponden a un usuario registrado. Verifique y vuelva a intentarlo."));
		}
		return "login?faces-redirect=true";

	}

	private String alumnoLogin(FacesContext context) throws Exception {
		Integer flag = null;
		flag = this.visaIntegration.verificaUsuarioExiste(getUsername(), getPassword());
		LOGGER.info(" verificaUsuarioExiste = " + flag);
		if (flag != null) {
			switch (flag.intValue()) {
			case 0:
				setTipoUsuarioLogueado(VisaIntegrationConstants.TIPO_USUARIO_ALUMNO);
				return "pagos?faces-redirect=true";
			case 1:
				context.addMessage("messaje", new FacesMessage(
						"El nombre de usuario y/o la contrase�a son incorrectos. Verifique y vuelva a intentarlo."));
				break;
			case 2:
				context.addMessage("messaje", new FacesMessage(
						"Ud. no est� matriculado en el presente periodo o ha dejado de estudiar en la instituci�n."));
				break;
			case 3:
				context.addMessage("messaje", new FacesMessage(
						"Ud. no tiene contrato activo con la instituci�n. Por favor, verifique y vuelva a intentarlo."));
				break;
			case 4:
				context.addMessage("messaje", new FacesMessage(
						"Ud. no est� programado para dictar curso alguno de esta instituci�n. Verifique y vuelva a intentarlo."));
				break;

			default:
				context
						.addMessage("messaje",
								new FacesMessage("Se ha producido un error inesperado. Por favor vuelva a intentarlo en unos minutos."));
				break;
			}
		}
		return "login?faces-redirect=true";
	}

}
