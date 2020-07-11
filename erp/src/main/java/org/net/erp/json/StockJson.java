package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.Stock;

public class StockJson {

	private Meta meta;

	private List<Stock> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Stock> getData() {
		return data;
	}

	public void setData(List<Stock> data) {
		this.data = data;
	}
	
	

}
