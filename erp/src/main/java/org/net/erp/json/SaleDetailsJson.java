package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.SaleDetails;

public class SaleDetailsJson {

	private Meta meta;

	private List<SaleDetails> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<SaleDetails> getData() {
		return data;
	}

	public void setData(List<SaleDetails> data) {
		this.data = data;
	}
	
	
}
