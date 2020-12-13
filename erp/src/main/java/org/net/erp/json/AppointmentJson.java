package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Appointment;

public class AppointmentJson {
	private int recordsTotal;
	private int recordsFiltered;
	private List<Appointment> data;
	
	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	/*
	 * public Meta getMeta() { return meta; }
	 * 
	 * public void setMeta(Meta meta) { this.meta = meta; }
	 */
	public List<Appointment> getData() {
		return data;
	}

	public void setData(List<Appointment> data) {
		this.data = data;
	}


}
