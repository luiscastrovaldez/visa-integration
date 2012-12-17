package com.visa.controller;

import java.io.InputStream;  

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;  
import javax.servlet.ServletContext;  
  
import org.primefaces.model.DefaultStreamedContent;  
import org.primefaces.model.StreamedContent;  

@ManagedBean
@ApplicationScoped
public class FileDownloadBean {

	private StreamedContent file;

	public FileDownloadBean() {
		InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
				.getResourceAsStream("/files/terminos-condiciones.pdf");
		file = new DefaultStreamedContent(stream, "application/pdf", "terminos-condiciones.pdf");
	}

	public StreamedContent getFile() {
		return file;
	}
}