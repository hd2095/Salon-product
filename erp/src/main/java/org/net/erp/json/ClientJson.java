package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Client;
import org.net.erp.model.Meta;

public class ClientJson {

	private Meta meta;
	
	private List<Client> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Client> getData() {
		return data;
	}

	public void setData(List<Client> data) {
		this.data = data;
	}
	
	
	
}
