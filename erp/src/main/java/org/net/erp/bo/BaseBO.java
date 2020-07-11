package org.net.erp.bo;

import org.net.erp.json.OperationStatus;
import org.net.erp.util.Constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseBO {

	/*
	 * 
	 * */
	public String setDeleteOperationStatus(boolean flag) {
		Gson gson = null;
		String json = null;
		OperationStatus status = null;
		try {
			gson = new GsonBuilder().setPrettyPrinting().create();
			status = new OperationStatus();
			if(flag) {
				status.setStatus(Constants.OP_STATUS_SUCCESSFUL);
			}else {
				status.setStatus(Constants.OP_STATUS_UNSUCCESSFUL);
			}
			json = gson.toJson(status);
		}catch(Exception e) {
			status.setStatus(Constants.OP_STATUS_UNSUCCESSFUL);
		}
		return json;
	}
}
