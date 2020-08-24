package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.OrderDetails;

public class OrderDetailsJson {
	
	private Meta meta;

	private List<OrderDetails> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<OrderDetails> getData() {
		return data;
	}

	public void setData(List<OrderDetails> data) {
		this.data = data;
	}
	
	
}
