package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.Master;

public class MasterJson {
	private Meta meta;

	private List<Master> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Master> getData() {
		return data;
	}

	public void setData(List<Master> data) {
		this.data = data;
	}
	
	
}
