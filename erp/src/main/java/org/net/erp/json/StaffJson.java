package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Staff;

public class StaffJson {

	private int recordsTotal;
	private int recordsFiltered;
	private List<Staff> data;

	public List<Staff> getData() {
		return data;
	}

	public void setData(List<Staff> data) {
		this.data = data;
	}
	
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
}
