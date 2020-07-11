package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Meta;
import org.net.erp.model.Services;

public class ServiceJson {

	private Meta meta;
	
	private List<Services> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Services> getData() {
		return data;
	}

	public void setData(List<Services> data) {
		this.data = data;
	}
	

	
}
