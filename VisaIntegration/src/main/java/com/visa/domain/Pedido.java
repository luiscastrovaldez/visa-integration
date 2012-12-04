package com.visa.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Pedido {

	@Attribute
	private String id;

	@Attribute
	private String eticket;

	@Element(required = false)
	private Operacion operacion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEticket() {
		return eticket;
	}

	public void setEticket(String eticket) {
		this.eticket = eticket;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

}
