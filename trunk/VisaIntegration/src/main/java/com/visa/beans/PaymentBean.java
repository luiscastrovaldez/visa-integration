package com.visa.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.visa.domain.Carrera;
import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;

@ManagedBean(name = "paymentBean")
@SessionScoped
public class PaymentBean implements Serializable {

  private String tipoUsuarioTexto;
  private List<Carrera> listaCarreras;
  private Carrera carrera;

  @ManagedProperty(value = "#{userManagedBean}")
  private UserManagedBean userManagedBean;

  @ManagedProperty(value = "#{visaIntegration}")
  VisaIntegration visaIntegration;

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
    listaCarreras = new ArrayList<Carrera>();
    System.out.println("userManagedBean = " + userManagedBean);
    System.out.println(" username = " + userManagedBean.getUsername());
    System.out.println(" clave = " + userManagedBean.getPassword());
    try {
      listaCarreras = visaIntegration.obtenerCarrerasPostgrado("CL20031506");
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

}
