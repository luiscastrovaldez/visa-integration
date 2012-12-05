package com.visa.xml.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

public class Campo {

	@Attribute
	private String id;

	@Text(required = false)
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
