package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Appointment;
import org.net.erp.model.Meta;

public class AppointmentJson {
	private Meta meta;

	private List<Appointment> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Appointment> getData() {
		return data;
	}

	public void setData(List<Appointment> data) {
		this.data = data;
	}


}
