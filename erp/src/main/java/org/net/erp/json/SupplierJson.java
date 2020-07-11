package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.Supplier;

public class SupplierJson {

	private Meta meta;

	private List<Supplier> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Supplier> getData() {
		return data;
	}

	public void setData(List<Supplier> data) {
		this.data = data;
	}



}
