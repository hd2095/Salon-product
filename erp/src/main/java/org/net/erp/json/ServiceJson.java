package org.net.erp.json;

import java.util.List;
import java.util.Map;

import org.net.erp.model.Meta;
import org.net.erp.model.Services;

public class ServiceJson {

	private Meta meta;
	
	private Map<String,List<Services>> data;
	
	private List<Services> listData;
	
	public List<Services> getListData() {
		return listData;
	}

	public void setListData(List<Services> listData) {
		this.listData = listData;
	}

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Map<String,List<Services>> getData() {
		return data;
	}

	public void setData(Map<String,List<Services>> data) {
		this.data = data;
	}
	

	
}
