package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.SalesNotInStock;

public class SalesNotInStockJson {
	private Meta meta;

	private List<SalesNotInStock> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<SalesNotInStock> getData() {
		return data;
	}

	public void setData(List<SalesNotInStock> data) {
		this.data = data;
	}
	
	
}
