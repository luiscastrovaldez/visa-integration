package com.visa.bo.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.visa.domain.TranVisaRespuesta;
import com.visa.util.VisaIntegrationConstants;

@ManagedBean(name = "visaResponseBean")
@SessionScoped
public class VisaResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(PaymentBean.class);

	private String numeroPedido;
	private String numeroTarjeta;
	private String fechaHoraPedido;
	private String importeTransaccion;
	private String descripcionProducto;
	private String codigoComprador;
	private String descripcionCodigo;
	private String mensaje;

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getFechaHoraPedido() {
		return fechaHoraPedido;
	}

	public void setFechaHoraPedido(String fechaHoraPedido) {
		this.fechaHoraPedido = fechaHoraPedido;
	}

	public String getImporteTransaccion() {
		return importeTransaccion;
	}

	public void setImporteTransaccion(String importeTransaccion) {
		this.importeTransaccion = importeTransaccion;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getCodigoComprador() {
		return codigoComprador;
	}

	public void setCodigoComprador(String codigoComprador) {
		this.codigoComprador = codigoComprador;
	}

	public String getDescripcionCodigo() {
		return descripcionCodigo;
	}

	public void setDescripcionCodigo(String descripcionCodigo) {
		this.descripcionCodigo = descripcionCodigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public VisaResponseBean() {
		final HttpSession session = getCurrentSession();
		if (session != null) {
			final String mensajeError = (String) session.getAttribute(VisaIntegrationConstants.CLAVE_RESPUESTA_ERROR_SESION);
			if (mensajeError != null && !mensajeError.isEmpty()) {
				setMensaje(mensajeError);
				LOGGER.info(mensajeError);
				return;
			}
			final TranVisaRespuesta tranVisaRespuesta = (TranVisaRespuesta) session.getAttribute(VisaIntegrationConstants.CLAVE_RESPUESTA_SESION);

			setNumeroPedido(tranVisaRespuesta.getnOrdenT());
			setNumeroTarjeta(tranVisaRespuesta.getPan());
			/* datos ke vienen de servicio*/
			setFechaHoraPedido(tranVisaRespuesta.getFechaHoraTx());
			setImporteTransaccion(tranVisaRespuesta.getImpAutorizado());
			setDescripcionProducto(tranVisaRespuesta.getDescripcionProducto());
			/* datos ke vienen de servicio*/
			setCodigoComprador(tranVisaRespuesta.getAlumno());
			setDescripcionCodigo(VisaIntegrationConstants.CODIGOSACCIONMAP.get(tranVisaRespuesta.getCodAccion()));
			setMensaje(VisaIntegrationConstants.CORRECTO_PROCESO_SOLICITUD);
		}
		LOGGER.info("No existe la sesion");
	}

	private HttpSession getCurrentSession() {
		final FacesContext context = FacesContext.getCurrentInstance();
		return (HttpSession) context.getExternalContext().getSession(false);
	}

}
