package com.visa.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Component;

import com.visa.domain.Carrera;
import com.visa.util.VisaIntegrationConstants;

@Component
@ManagedBean
@ApplicationScoped
public class PaymentBean {

	private String tipoUsuarioTexto;
	private List<Carrera> listaCarreras;
	private Carrera carrera;

	public PaymentBean() {
		listaCarreras = new ArrayList<Carrera>();
		// TODO get user type
		tipoUsuarioTexto = VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_ALUMNO;
	}

	public String getTipoUsuarioTexto() {
		return tipoUsuarioTexto;
	}

	public void setTipoUsuarioTexto(final String tipoUsuarioTexto) {
		this.tipoUsuarioTexto = tipoUsuarioTexto;
	}

	public List<Carrera> getListaCarreras() {
		return listaCarreras;
	}

	public void setListaCarreras(List<Carrera> listaCarreras) {
		this.listaCarreras = listaCarreras;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

}
