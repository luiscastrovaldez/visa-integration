package com.visa.beans;

import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@ApplicationScoped
public class UserManagedBean {
	UserService userService = new UserService();

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

	public void savePerson(){
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
		if ("test".equalsIgnoreCase(getUsername())
				&& "test".equals(getPassword())) {
			return "home";
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("username", new FacesMessage(
					"Invalid UserName and Password"));
			return "login";
		}
	}

	public String searchUser() {
		String username = (this.searchUser == null) ? "" : this.searchUser
				.trim();
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
}
