package com.visa.beans;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.visa.domain.LogTransaction;
import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;

//@Component
@ManagedBean(name="userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable{

	UserService userService = new UserService();

	@Autowired
	VisaIntegration visaIntegration;

	public void setVisaIntegration(VisaIntegration visaIntegration) {
		this.visaIntegration = visaIntegration;
	}

	private String username;
	private String password;
	private String searchUser;
	private Collection<User> searchUsersResults;
	private User selectedUser;

	private String firstname;
	private String surname;

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
		System.out.println("user " + getUsername());

		/*LogTransaction logTransaction = visaIntegration.findById(1L);
		if (logTransaction != null) {
			System.out.println(" id = " + logTransaction.getId());
		}*/

		if ("test".equalsIgnoreCase(getUsername()) && "test".equals(getPassword())) {
			return "pagos";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("username", new FacesMessage("Invalid UserName and Password"));
			return "login";
		}
	}

	public String searchUser() {
		String username = (this.searchUser == null) ? "" : this.searchUser.trim();
		this.searchUsersResults = userService.searchUsers(username);
		System.out.println(searchUsersResults);
		return "home";
	}

	public String updateUser() {
		userService.update(this.selectedUser);
		return "home";
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

}
