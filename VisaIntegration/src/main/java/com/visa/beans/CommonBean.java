package com.visa.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.visa.util.VisaIntegrationConstants;

@ManagedBean
@ApplicationScoped
public class CommonBean {

	public String getAppTitle() {
		return VisaIntegrationConstants.APP_TITULO;
	}
}
