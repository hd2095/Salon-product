package org.net.erp.json;

import java.util.List;

import org.net.erp.model.AppointmentDetails;
import org.net.erp.model.Meta;

public class AppointmentDetailsJson {
	private Meta meta;

	private List<AppointmentDetails> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<AppointmentDetails> getData() {
		return data;
	}

	public void setData(List<AppointmentDetails> data) {
		this.data = data;
	}
	
	
}
