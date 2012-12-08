package com.visa.web.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
@ApplicationScoped
public class ButtonBean {

	 public void save() {  
	        addMessage("Data saved");  
	    }  
	      
	    public void update() {  
	        addMessage("Data updated");  
	    }  
	      
	    public void delete() {  
	        addMessage("Data deleted");  
	    }  
	      
	    public void addMessage(String summary) {  
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
	        FacesContext.getCurrentInstance().addMessage(null, message);  
	    }  
	
}
