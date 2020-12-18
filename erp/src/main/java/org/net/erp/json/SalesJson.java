package org.net.erp.json;

import java.util.List;

//import org.net.erp.model.Meta;
import org.net.erp.model.Sales;

public class SalesJson {
	//private Meta meta;

	private int recordsTotal;
	private int recordsFiltered;
	private List<Sales> data;

	/* public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}
	 */
	
	public List<Sales> getData() {
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

	public void setData(List<Sales> data) {
		this.data = data;
	}


}
