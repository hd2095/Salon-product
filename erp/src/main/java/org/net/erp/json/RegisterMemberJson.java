package org.net.erp.json;

import java.util.List;

import org.net.erp.model.RegisterMember;
import org.net.erp.model.Meta;

public class RegisterMemberJson {
	private Meta meta;

	private List<RegisterMember> data;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<RegisterMember> getData() {
		return data;
	}

	public void setData(List<RegisterMember> data) {
		this.data = data;
	}
}
