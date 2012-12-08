package com.visa.bo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.apache.log4j.Logger;

import com.visa.domain.Carrera;
import com.visa.domain.Concepto;
import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;

@ManagedBean(name = "paymentBean")
@ViewScoped
public class PaymentBean implements Serializable {

  private static final long serialVersionUID = 1L;
  private static final Logger LOGGER = Logger.getLogger(PaymentBean.class);

  private String tipoUsuarioTexto;
  private List<Carrera> listaCarreras;
  private List<Concepto> listaConceptos;
  private List<Concepto> listaConceptosSeleecionados;
  private Carrera carrera;
  

  @ManagedProperty(value = "#{userManagedBean}")
  private UserManagedBean userManagedBean;

  @ManagedProperty(value = "#{visaIntegration}")
  private VisaIntegration visaIntegration;

  public UserManagedBean getUserManagedBean() {
    return userManagedBean;
  }

  public void setUserManagedBean(UserManagedBean userManagedBean) {
    this.userManagedBean = userManagedBean;
  }

  public VisaIntegration getVisaIntegration() {
    return visaIntegration;
  }

  public void setVisaIntegration(VisaIntegration visaIntegration) {
    this.visaIntegration = visaIntegration;
  }

  public PaymentBean() {
    LOGGER.info("--------------PaymentBean------------");
    tipoUsuarioTexto = VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_ALUMNO;
  }

  public String getTipoUsuarioTexto() {
    return tipoUsuarioTexto;
  }

  public void setTipoUsuarioTexto(final String tipoUsuarioTexto) {
    this.tipoUsuarioTexto = tipoUsuarioTexto;
  }

  public List<Carrera> getListaCarreras() {
    LOGGER.info("getListaCarreras");
    LOGGER.info("usuario = " + userManagedBean.getUsername());
    LOGGER.info("tipo usuario = " + userManagedBean.getTipoUsuarioLogueado());
    LOGGER.info("num atencion = " + userManagedBean.getNumAtencion());
    listaCarreras = new ArrayList<Carrera>();
    try {
    	    	
      if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(userManagedBean.getTipoUsuarioLogueado())) {
    	  listaCarreras = visaIntegration.obtenerCarrerasPostgrado(userManagedBean.getUsername());
            
      } else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(userManagedBean.getTipoUsuarioLogueado())) {
    	  listaCarreras = visaIntegration.obtenerCarrerasPostulante(userManagedBean.getUsername());
    	  
      } else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(userManagedBean.getTipoUsuarioLogueado())) {
    	  listaCarreras = listaCarreras = visaIntegration.obtenerCarrerasProspecto(userManagedBean.getUsername(), userManagedBean.getNumAtencion());
        
      }
      
      
      
      

    } catch (Exception e) {
      e.printStackTrace();
    }

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

  public List<Concepto> getListaConceptos() {
    LOGGER.info("getListaConceptos");
    listaConceptos = new ArrayList<Concepto>();
    try {
      if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(userManagedBean.getTipoUsuarioLogueado())
          && (carrera != null)) {
        listaConceptos = visaIntegration.obtenerCuotasActuales(userManagedBean.getUsername(), carrera.getCodigo());
      } else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(userManagedBean.getTipoUsuarioLogueado())) {
        listaConceptos = visaIntegration.obtenerListarCuotasPostulante(userManagedBean.getUsername());
      } else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(userManagedBean.getTipoUsuarioLogueado())) {
        listaConceptos = visaIntegration.obtenerListarCuotasProspecto(userManagedBean.getUsername(), userManagedBean.getNumAtencion());
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return listaConceptos;
  }

  public void setListaConceptos(List<Concepto> listaConceptos) {
    this.listaConceptos = listaConceptos;
  }

  public List<Concepto> getListaConceptosSeleecionados() {
    return listaConceptosSeleecionados;
  }

  public void setListaConceptosSeleecionados(List<Concepto> listaConceptosSeleecionados) {
    this.listaConceptosSeleecionados = listaConceptosSeleecionados;
  }

  public void cambioCarrera() {
    LOGGER.info("cambioCarrera");
    if (carrera != null && (carrera.getCodigo().length() > 0)) {
      getListaConceptos();
    }
    else {
      listaConceptos = new ArrayList<Concepto>();
    }
  }

}
