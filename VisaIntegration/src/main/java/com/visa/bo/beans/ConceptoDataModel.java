package com.visa.bo.beans;

import java.util.List;

import org.apache.log4j.Logger;

import com.visa.domain.Concepto;
import com.visa.web.beans.PrimeDataModel;

public class ConceptoDataModel extends PrimeDataModel<Concepto> {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = Logger.getLogger(ConceptoDataModel.class);

	public ConceptoDataModel() {
	}

	public ConceptoDataModel(List<Concepto> data) {
		super(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Concepto getRowData(String rowKeyStr) {
		LOGGER.info("get Row Data: " + rowKeyStr);
		int rowKey;
		try {
			rowKey = Integer.valueOf(rowKeyStr);
		} catch (Exception ex) {
			rowKey = 0;
		}
		List<Concepto> conceptos = (List<Concepto>) getWrappedData();

		for (final Concepto concepto : conceptos) {
			if (concepto.getItem() == rowKey)
				return concepto;
		}

		return null;
	}

	@Override
	public Object getRowKey(Concepto concepto) {
		LOGGER.info("get Row Key: " + concepto.getItem());
		return concepto.getItem();
	}

}
