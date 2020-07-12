package org.net.erp.json;

import java.util.List;

import org.net.erp.model.Category;
import org.net.erp.model.Meta;

public class CategoryJson {

	private Meta meta;

	private List<Category> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Category> getData() {
		return data;
	}

	public void setData(List<Category> data) {
		this.data = data;
	}

}

