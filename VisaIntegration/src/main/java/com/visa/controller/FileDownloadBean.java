package com.visa.controller;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name="fileDownloadBean")
@RequestScoped
public class FileDownloadBean {

	private StreamedContent file;

	
	
	/*public FileDownloadBean() {
		try {
			InputStream stream = null;
			stream = ((ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext())
					.getResourceAsStream("/files/terminos-condiciones.pdf");
			
			file = new DefaultStreamedContent(stream, "application/pdf",
					"terminos-condiciones.pdf");
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}*/

	public StreamedContent getFile() {
		try {
			InputStream stream = null;
			stream = ((ServletContext) FacesContext
					.getCurrentInstance().getExternalContext().getContext())
					.getResourceAsStream("/files/terminos-condiciones.pdf");
			
			file = new DefaultStreamedContent(stream, "application/pdf",
					"terminos-condiciones.pdf");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return file;
	}
}