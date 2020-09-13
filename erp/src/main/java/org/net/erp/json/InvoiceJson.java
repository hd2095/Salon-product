package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.Invoice;

public class InvoiceJson {
	private Meta meta;

	private List<Invoice> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Invoice> getData() {
		return data;
	}

	public void setData(List<Invoice> data) {
		this.data = data;
	}
	
	
}
