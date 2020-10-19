package org.net.erp.json;

import java.util.List;

import org.net.erp.model.UpgradeToPro;
import org.net.erp.model.Meta;

public class UpgradeToProJson {

	private Meta meta;

	private List<UpgradeToPro> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<UpgradeToPro> getData() {
		return data;
	}

	public void setData(List<UpgradeToPro> data) {
		this.data = data;
	}
	
	

}
