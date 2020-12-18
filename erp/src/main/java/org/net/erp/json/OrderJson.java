package org.net.erp.json;

import java.util.List;
import org.net.erp.model.Order;

public class OrderJson {

	private int recordsTotal;
	private int recordsFiltered;
	private List<Order> data;
	
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
	public List<Order> getData() {
		return data;
	}

	public void setData(List<Order> data) {
		this.data = data;
	}
	
	

	
}
