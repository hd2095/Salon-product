package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Staff;
import org.net.erp.model.Meta;

public class StaffJson {

	private Meta meta;
	
	private List<Staff> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Staff> getData() {
		return data;
	}

	public void setData(List<Staff> data) {
		this.data = data;
	}
	
}
