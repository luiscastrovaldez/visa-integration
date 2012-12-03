package com.visa.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Pedido {

	@Attribute
	private int id;

	@Attribute
	private String eticket;

	@Element
	private Operacion operacion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
