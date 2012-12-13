package com.visa.bo.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "visaResponseBean")
@SessionScoped
public class VisaResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

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

}
