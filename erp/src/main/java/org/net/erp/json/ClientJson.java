package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Client;


public class ClientJson {

	private int recordsTotal;
	private int recordsFiltered;
	private List<Client> data;


	public List<Client> getData() {
		return data;
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

	public void setData(List<Client> data) {
		this.data = data;
	}



}
