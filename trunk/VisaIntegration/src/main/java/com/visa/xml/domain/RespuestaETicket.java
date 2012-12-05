package com.visa.xml.domain;

import java.util.List;

import org.simpleframework.xml.ElementList;

public class RespuestaETicket {

	@ElementList(entry = "campo", inline = false)
	private List<Campo> registro;

	@ElementList(entry = "mensaje", inline = false)
	private List<Mensaje> mensajes;

	public List<Campo> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Campo> registro) {
		this.registro = registro;
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

}
