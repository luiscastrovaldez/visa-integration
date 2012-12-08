package com.visa.bo.beans;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;

@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(UserManagedBean.class);

  @ManagedProperty(value = "#{visaIntegration}")
  private VisaIntegration visaIntegration;

  public VisaIntegration getVisaIntegration() {
    return visaIntegration;
  }

  public void setVisaIntegration(VisaIntegration visaIntegration) {
    this.visaIntegration = visaIntegration;
  }

  private String username;
  private String password;
  private int numAtencion;
  private String tipoUsuarioLogueado;
  private String searchUser;
  private Collection<User> searchUsersResults;
  private User selectedUser;

  private String firstname;
  private String surname;

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

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public void savePerson() {
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + firstname + " " + surname + "!"));
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

  public User getSelectedUser() {
    if (selectedUser == null) {
      selectedUser = new User();
    }
    return selectedUser;
  }

  public void setSelectedUser(User selectedUser) {
    this.selectedUser = selectedUser;
  }

  public Collection<User> getSearchUsersResults() {
    return searchUsersResults;
  }

  public void setSearchUsersResults(Collection<User> searchUsersResults) {
    this.searchUsersResults = searchUsersResults;
  }

  public String getSearchUser() {
    return searchUser;
  }

  public void setSearchUser(String searchUser) {
    this.searchUser = searchUser;
  }

  public String login() {
    LOGGER.info("usuario" + getUsername());

    if ("test".equals(getPassword())) {
      setTipoUsuarioLogueado("0");
      return "pagos";
    } else {
      FacesContext context = FacesContext.getCurrentInstance();
      context.addMessage("username", new FacesMessage("Invalid UserName and Password"));
      return "login";
    }
  }

  public void onUserSelect(SelectEvent event) {
    selectedUser = (User) event.getObject();
    System.out.println("selectedUser = " + selectedUser);
  }

  public void onUserUnselect(UnselectEvent event) {
    selectedUser = null;
  }

  public void cerrarSesion() {
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

  private String getTipoUsuario() {
    final HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    return request.getParameter("usr") == null ? VisaIntegrationConstants.TIPO_USUARIO_ALUMNO : request.getParameter("usr");
  }
  
	public String loginAlumno() throws Exception {

		Integer flag = null;
		FacesContext context = FacesContext.getCurrentInstance();;
		try {
			LOGGER.info("usuario" + getUsername());

			setTipoUsuarioLogueado("0");

			flag = this.visaIntegration.verificaUsuarioExiste(getUsername(),getPassword());
			LOGGER.info(" verificaUsuarioExiste = " + flag);
			if (flag != null) {
				switch (flag.intValue()) {
				case 0:
					return "pagos";					
				case 1:
					context.addMessage("messaje", new FacesMessage("El nombre de usuario y/o la contraseña son incorrectos. Verifique y vuelva a intentarlo."));
					break;
				case 2:
					context.addMessage("messaje", new FacesMessage("Ud. no está matriculado en el presente periodo o ha dejado de estudiar en la institución."));
					break;
				case 3:
					context.addMessage("messaje", new FacesMessage("Ud. no tiene contrato activo con la institución. Por favor, verifique y vuelva a intentarlo."));
					break;
				case 4:
					context.addMessage("messaje", new FacesMessage("Ud. no está programado para dictar curso alguno de esta institución. Verifique y vuelva a intentarlo."));
					break;	

				default:
					context.addMessage("messaje", new FacesMessage("Se ha producido un error inesperado. Por favor vuelva a intentarlo en unos minutos."));
					break;
				}								
			} 

			return "login";
		} catch (Exception e) {
			e.printStackTrace();
			context = FacesContext.getCurrentInstance();
			context.addMessage("messaje", new FacesMessage(e.getMessage()));
			return "error";
		}

	}
  

}
