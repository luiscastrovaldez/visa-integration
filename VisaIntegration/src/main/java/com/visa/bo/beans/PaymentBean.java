package com.visa.bo.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.visa.domain.Carrera;
import com.visa.domain.Concepto;
import com.visa.services.VisaIntegration;
import com.visa.util.VisaIntegrationConstants;
import com.visa.util.VisaIntegrationUtil;
import com.visa.xml.domain.NuevoETicket;
import com.visa.xml.domain.Parametro;
import com.visa.xml.services.VisaXmlParserService;

@ManagedBean(name = "paymentBean")
@SessionScoped
public class PaymentBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(PaymentBean.class);

	private String tipoUsuarioTexto;
	private List<Carrera> listaCarreras;
	private Concepto[] listaConceptosSeleccionados;
	private Carrera carrera;
	private Concepto concepto;
	private ConceptoDataModel conceptosModel;
	private String montoTotal;
	private String totalVisa;
	private boolean pagarHabilitado;
	private String visaXmlData;

	@ManagedProperty(value = "#{userManagedBean}")
	private UserManagedBean userManagedBean;

	@ManagedProperty(value = "#{visaIntegration}")
	private VisaIntegration visaIntegration;

	@ManagedProperty(value = "#{visaXmlParserServiceImpl}")
	private VisaXmlParserService visaXmlParserService;

	public PaymentBean() {
		LOGGER.info("--------------PaymentBean------------");
		final String tipoUsuario = userManagedBean.getTipoUsuarioLogueado();
		LOGGER.info("tipoUsuario: " + tipoUsuario);
		if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(tipoUsuario)) {
			setTipoUsuarioTexto(VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_ALUMNO);
		} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(tipoUsuario)) {
			setTipoUsuarioTexto(VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_POSTULANTE);
		} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(tipoUsuario)) {
			setTipoUsuarioTexto(VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_PROSPECTO);
		}
	}

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

	public VisaXmlParserService getVisaXmlParserService() {
		return visaXmlParserService;
	}

	public void setVisaXmlParserService(VisaXmlParserService visaXmlParserService) {
		this.visaXmlParserService = visaXmlParserService;
	}

	public String getTipoUsuarioTexto() {
		return tipoUsuarioTexto;
	}

	public void setTipoUsuarioTexto(final String tipoUsuarioTexto) {
		this.tipoUsuarioTexto = tipoUsuarioTexto;
	}

	public ConceptoDataModel getConceptosModel() {
		return conceptosModel;
	}

	public void setConceptosModel(ConceptoDataModel conceptosModel) {
		this.conceptosModel = conceptosModel;
	}

	public Concepto[] getListaConceptosSeleccionados() {
		return listaConceptosSeleccionados;
	}

	public void setListaConceptosSeleccionados(Concepto[] listaConceptosSeleccionados) {
		this.listaConceptosSeleccionados = listaConceptosSeleccionados;
	}

	public List<Carrera> getListaCarreras() {
		LOGGER.info("getListaCarreras");
		LOGGER.info("usuario = " + userManagedBean.getUsername());
		LOGGER.info("tipo usuario = " + userManagedBean.getTipoUsuarioLogueado());
		LOGGER.info("num atencion = " + userManagedBean.getNumAtencion());
		if (listaCarreras != null) {
			return listaCarreras;
		}
		listaCarreras = new ArrayList<Carrera>();
		try {

			if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaCarreras = visaIntegration.obtenerCarrerasPostgrado(userManagedBean.getUsername());

			} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaCarreras = visaIntegration.obtenerCarrerasPostulante(userManagedBean.getUsername());

			} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaCarreras = visaIntegration.obtenerCarrerasProspecto(userManagedBean.getUsername(), userManagedBean.getNumAtencion());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (listaCarreras.size() > 1) {
			carrera = listaCarreras.get(0);
		}
		LOGGER.info("cantidad de carreras: " + listaCarreras.size());
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

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public List<Concepto> getListaConceptos() {
		LOGGER.info("getListaConceptos");
		LOGGER.info("Tipo Usuario Logueado: " + userManagedBean.getTipoUsuarioLogueado());
		LOGGER.info("Carrera: " + carrera);
		List<Concepto> listaConceptos = new ArrayList<Concepto>();
		try {
			// TODO remover linea
			listaConceptos = visaIntegration.obtenerCuotasActuales("W200932473", "113");
			conceptosModel = new ConceptoDataModel(listaConceptos);
			if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(userManagedBean.getTipoUsuarioLogueado()) && (carrera != null)) {
				listaConceptos = visaIntegration.obtenerCuotasActuales("W200932473", "113");
			} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaConceptos = visaIntegration.obtenerListarCuotasPostulante(userManagedBean.getUsername());
			} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaConceptos = visaIntegration.obtenerListarCuotasProspecto(userManagedBean.getUsername(), userManagedBean.getNumAtencion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("cantidad de conceptos: " + listaConceptos.size());
		double monto = 0;
		for (final Concepto concepto : listaConceptos) {
			monto += Double.valueOf(concepto.getMonto());
		}
		setMontoTotal(Double.toString(monto));
		return listaConceptos;
	}

	public void cambioCarrera() {
		LOGGER.info("cambioCarrera");
		if (carrera != null && (carrera.getCodigo().length() > 0)) {
			LOGGER.info("carrera " + carrera);
			LOGGER.info("carrera codigo " + carrera.getCodigo());
			getListaConceptos();
		}
	}

	public String registrarPago() {
		LOGGER.info("registrarPago");
		final NuevoETicket nuevoETicket = new NuevoETicket();
		final ArrayList<Parametro> parametros = new ArrayList<Parametro>();
		Parametro parametro = new Parametro(VisaIntegrationConstants.CAMPO_CANAL, VisaIntegrationConstants.CAMPO_CANAL_VALOR);
		parametros.add(parametro);
		parametro = new Parametro(VisaIntegrationConstants.CAMPO_PRODUCTO, VisaIntegrationConstants.CAMPO_PRODUCTO_VALOR);
		parametros.add(parametro);
		// TODO obtener el numero de tienda
		parametro = new Parametro(VisaIntegrationConstants.CAMPO_COD_TIENDA, VisaIntegrationConstants.CODIGO_TIENDA);
		parametros.add(parametro);
		// TODO obtener el numero de orden
		parametro = new Parametro(VisaIntegrationConstants.CAMPO_NUM_ORDEN, "");
		parametros.add(parametro);
		parametro = new Parametro(VisaIntegrationConstants.CAMPO_MOUNT, obtenerMontoTransaccionVisa());
		parametros.add(parametro);
		parametro = new Parametro(VisaIntegrationConstants.CAMPO_DATO_COMERCIO, "");
		parametros.add(parametro);
		nuevoETicket.setParametros(parametros);
		setVisaXmlData(visaXmlParserService.parseVisaNewETicketRequestToXml(nuevoETicket));
		// TODO llamar SP para registrar pago y obtener num de orden
		return "envioVisa";

	}

	public void totalizarPagos() {
		LOGGER.info("totalizarPagos");

	}

	public String getMontoTotal() {
		return VisaIntegrationUtil.formatDoubleString(montoTotal);
	}

	public void setMontoTotal(String montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getTotalVisa() {
		return VisaIntegrationUtil.formatDoubleString(totalVisa);
	}

	public void setTotalVisa(String totalVisa) {
		this.totalVisa = totalVisa;
	}

	public boolean isPagarHabilitado() {
		return pagarHabilitado;
	}

	public void setPagarHabilitado(boolean pagarHabilitado) {
		this.pagarHabilitado = pagarHabilitado;
	}

	public String getVisaXmlData() {
		LOGGER.info("getVisaXmlData");
		LOGGER.info(visaXmlData);
		return visaXmlData;
	}

	public void setVisaXmlData(String visaXmlData) {
		this.visaXmlData = visaXmlData;
	}

	private String obtenerMontoTransaccionVisa() {
		// TODO Auto-generated method stub
		return null;
	}

}