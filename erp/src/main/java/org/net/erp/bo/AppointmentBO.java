package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.AppointmentJson;
import org.net.erp.model.Appointment;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class AppointmentBO extends BaseBO{
	/*
	 * 
	 * */
	public String parseFetchAppointment(List<Appointment> appointments) {
		Gson gson = null;
		AppointmentJson appointmentJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField(Constants.APPOINTMENT_FIELD);
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(appointments.size());
			appointmentJson = new AppointmentJson();
			appointmentJson.setData(appointments);
			appointmentJson.setMeta(meta);
			json = gson.toJson(appointmentJson);
			}
	catch(Exception e) {
		
	}
		return json;
	}
}
