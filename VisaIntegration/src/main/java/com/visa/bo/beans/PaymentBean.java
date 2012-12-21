package com.visa.bo.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.ViewExpiredException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	private boolean pagarDeshabilitado;
	private boolean aceptaTermino;
	private boolean habilitaCheck;
	private String visaXmlData;

	@ManagedProperty(value = "#{userManagedBean}")
	private UserManagedBean userManagedBean;

	@ManagedProperty(value = "#{visaIntegration}")
	private VisaIntegration visaIntegration;

	@ManagedProperty(value = "#{visaXmlParserServiceImpl}")
	private VisaXmlParserService visaXmlParserService;

	public PaymentBean() {
		LOGGER.info("--------------PaymentBean------------");			
		
	}

	public boolean isHabilitaCheck() {
		return habilitaCheck;
	}

	public void setHabilitaCheck(boolean habilitaCheck) {
		this.habilitaCheck = habilitaCheck;
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
		final String tipoUsuario = userManagedBean.getTipoUsuarioLogueado();
		LOGGER.info("tipoUsuario: " + tipoUsuario);
		if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(tipoUsuario)) {
			tipoUsuarioTexto =  VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_ALUMNO;
		} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(tipoUsuario)) {
			tipoUsuarioTexto = VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_POSTULANTE;
		} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(tipoUsuario)) {
			tipoUsuarioTexto = VisaIntegrationConstants.CODIGO_USUARIO_TEXTO + VisaIntegrationConstants.USUARIO_PROSPECTO;
		}
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
				listaCarreras = visaIntegration.obtenerCarrerasPostgrado(userManagedBean.getUsername().substring(1));

			} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaCarreras = visaIntegration.obtenerCarrerasPostulante(userManagedBean.getUsername());

			} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaCarreras = visaIntegration.obtenerCarrerasProspecto(userManagedBean.getUsername(), userManagedBean.getNumAtencion());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (listaCarreras.size() > 0) {
			carrera = listaCarreras.get(0);
			getListaConceptos();
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
			if (VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(userManagedBean.getTipoUsuarioLogueado()) && (carrera != null)) {
				listaConceptos = visaIntegration.obtenerCuotasActuales(userManagedBean.getUsername().substring(1), carrera.getCodigo());
			} else if (VisaIntegrationConstants.TIPO_USUARIO_POSTULANTE.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaConceptos = visaIntegration.obtenerListarCuotasPostulante(userManagedBean.getUsername());
			} else if (VisaIntegrationConstants.TIPO_USUARIO_PROSPECTO.equals(userManagedBean.getTipoUsuarioLogueado())) {
				listaConceptos = visaIntegration.obtenerListarCuotasProspecto(userManagedBean.getUsername(), userManagedBean.getNumAtencion());
			}
			conceptosModel = new ConceptoDataModel(listaConceptos);
		} catch (Exception e) {
			LOGGER.error("Error al traer la lista de conceptos", e);
		}
		LOGGER.info("cantidad de conceptos: " + listaConceptos.size());
		setPagarDeshabilitado(listaConceptos.size() == 0);
		setHabilitaCheck(listaConceptos.size() == 0);
		setPagarDeshabilitado(!aceptaTermino);
		
		double monto = 0;
		for (final Concepto concepto : listaConceptos) {
			monto += Double.valueOf(concepto.getMonto());
		}
		setMontoTotal(Double.toString(monto));
		return listaConceptos;
	}

	public void aceptaTerminosCondiciones() {

		setPagarDeshabilitado(!aceptaTermino);

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
		if (listaConceptosSeleccionados == null || listaConceptosSeleccionados.length == 0) {
			return "pagos";
		}
		LOGGER.info("registrarPago");
		final String usuario = VisaIntegrationConstants.TIPO_USUARIO_ALUMNO.equals(userManagedBean.getTipoUsuarioLogueado())?
		userManagedBean.getUsername().substring(1): userManagedBean.getUsername();
		final Integer numOperacion = registraTransaccionVisa(carrera.getCodigo(), usuario, BigDecimal.valueOf(Double.parseDouble(getTotalVisa())),
		    listaConceptosSeleccionados[0].getPeriodoacademico(), Integer.toString(userManagedBean.getNumAtencion()),
		    Arrays.asList(listaConceptosSeleccionados));
		if (numOperacion > 0) {
			final NuevoETicket nuevoETicket = new NuevoETicket();
			final ArrayList<Parametro> parametros = new ArrayList<Parametro>();
			Parametro parametro = new Parametro(VisaIntegrationConstants.CAMPO_CANAL, VisaIntegrationConstants.CAMPO_CANAL_VALOR);
			parametros.add(parametro);
			parametro = new Parametro(VisaIntegrationConstants.CAMPO_PRODUCTO, VisaIntegrationConstants.CAMPO_PRODUCTO_VALOR);
			parametros.add(parametro);
			parametro = new Parametro(VisaIntegrationConstants.CAMPO_COD_TIENDA, VisaIntegrationConstants.CODIGO_TIENDA);
			parametros.add(parametro);
			parametro = new Parametro(VisaIntegrationConstants.CAMPO_NUM_ORDEN, numOperacion.toString());
			parametros.add(parametro);
			parametro = new Parametro(VisaIntegrationConstants.CAMPO_MOUNT, obtenerMontoTransaccionVisa(numOperacion));
			parametros.add(parametro);
			parametro = new Parametro(VisaIntegrationConstants.CAMPO_DATO_COMERCIO, "");
			parametros.add(parametro);
			nuevoETicket.setParametros(parametros);
			setVisaXmlData(visaXmlParserService.parseVisaNewETicketRequestToXml(nuevoETicket));
			final HttpSession session = getCurrentSession();
			session.setAttribute(VisaIntegrationConstants.CLAVE_USUARIO_SESION, userManagedBean.getUsername());
			session.setAttribute(VisaIntegrationConstants.CLAVE_CARRERA_SESION, carrera.getCodigo());
			session.setAttribute(VisaIntegrationConstants.CLAVE_TIPO_USUARIO_SESION, userManagedBean.getTipoUsuarioLogueado());
			limpiarVariables();
			return "envioVisa";
		} else {
			final FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("message", new FacesMessage("Ha ocurrido un error, su operación no puede procesarse. Por favor, vuelva a intentarlo en unos minutos."));
			return "pagos";
		}
	}

	public void totalizarPagos() {
		LOGGER.info("totalizarPagos");
		LOGGER.info("listaConceptosSeleccionados: " + listaConceptosSeleccionados.length);
		double totalVisaNum = 0;
		for (final Concepto concepto : listaConceptosSeleccionados) {
			if (concepto.getRecibo() == null || concepto.getRecibo().isEmpty()) {
				double monto;
				try {
					monto = Double.valueOf(concepto.getMonto());
				} catch (Exception e) {
					monto = 0;
				}
				totalVisaNum += monto;
			}
		}
		setTotalVisa(Double.toString(totalVisaNum));		
		LOGGER.info("total Visa: " + getTotalVisa());
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

	public boolean isPagarDeshabilitado() {
		return pagarDeshabilitado;
	}

	public void setPagarDeshabilitado(boolean pagarDeshabilitado) {
		this.pagarDeshabilitado = pagarDeshabilitado;
	}

	public boolean isAceptaTermino() {
		return aceptaTermino;
	}

	public void setAceptaTermino(boolean aceptaTermino) {
		this.aceptaTermino = aceptaTermino;
	}

	public String getVisaXmlData() {
		LOGGER.info("getVisaXmlData");
		LOGGER.info(visaXmlData);
		return visaXmlData;
	}

	public void setVisaXmlData(String visaXmlData) {
		this.visaXmlData = visaXmlData;
	}

	public String estiloCelda(final String recibo) {
		return recibo != null && !recibo.isEmpty() ? "cuotaPagada": "cuotaNoPagada";
	}

	private String obtenerMontoTransaccionVisa(final Integer idTransaccion) {
		try {
			return VisaIntegrationUtil.formatBigDecimal(visaIntegration.obtenerMontoTransaccionVisa(idTransaccion));
		} catch (Exception e) {
			LOGGER.error("No se pudo obtener el monto", e);
			return "0.00";
		}
	}

	private Integer registraTransaccionVisa(final String codigoCarrera, final String codigoUsuario, final BigDecimal monto,
			final String periodoAcademico, final String cadenaValores, final List<Concepto> listaDetalle) {
		try {
			return visaIntegration.registraTransaccionVisa(codigoCarrera, codigoUsuario, monto, periodoAcademico, cadenaValores, listaDetalle);
		} catch (Exception e) {
			LOGGER.error("No se pudo registrar la operacion", e);
			return Integer.valueOf(0);
		}
	}

	private HttpSession getCurrentSession() {
		final FacesContext context = FacesContext.getCurrentInstance();
		return (HttpSession) context.getExternalContext().getSession(false);
	}

	private void limpiarVariables() {
		listaCarreras = null;
		listaConceptosSeleccionados = null;		
		totalVisa = "0.00";
		setAceptaTermino(Boolean.FALSE);		
	}

}
