package com.visa.domain;

import java.util.ArrayList;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "consulta_eticket")
public class ConsultaETicket {

	@ElementList(entry = "parametro", inline = false)
	private ArrayList<Parametro> parametros;

	public ArrayList<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(final ArrayList<Parametro> parametros) {
		this.parametros = parametros;
	}

}
