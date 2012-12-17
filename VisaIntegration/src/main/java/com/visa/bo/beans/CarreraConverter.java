package com.visa.bo.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.log4j.Logger;

import com.visa.domain.Carrera;

@ManagedBean(name = "carreraConverter")
@ViewScoped
public class CarreraConverter implements Converter {

	private static final Logger LOGGER = Logger.getLogger(CarreraConverter.class);

	@ManagedProperty(value = "#{paymentBean}")
	private PaymentBean paymentBean;

	public PaymentBean getPaymentBean() {
		return paymentBean;
	}

	public void setPaymentBean(PaymentBean paymentBean) {
		this.paymentBean = paymentBean;
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
		LOGGER.info("get As Object: " + submittedValue);
		if (submittedValue.trim().equals("")) {
			return null;
		} else {
			for (Carrera carrera : paymentBean.getListaCarreras()) {
				LOGGER.info("carrera codigo: " + carrera.getCodigo());
				if (carrera.getCodigo().equals(submittedValue)) {
					return carrera;
				}
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		LOGGER.info("get As String: " + value);
		if (value == null || value.equals("")) {
			return "";
		} else {
			return String.valueOf(((Carrera) value).getCodigo());
		}
	}

}
