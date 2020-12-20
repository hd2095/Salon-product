package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.AppointmentDetailsJson;
import org.net.erp.json.AppointmentJson;
import org.net.erp.json.UnavailableMessageJson;
import org.net.erp.model.Appointment;
import org.net.erp.model.AppointmentDetails;
import org.net.erp.model.Meta;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class AppointmentBO extends BaseBO{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentBO.class);
	
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
			appointmentJson = new AppointmentJson();
			appointmentJson.setData(appointments);
			appointmentJson.setRecordsFiltered(appointments.size());
			appointmentJson.setRecordsTotal(appointments.size());
			json = gson.toJson(appointmentJson);
		}
		catch(Exception e) {
			LOGGER.error("Exception in parseFetchAppointment :: "+e.getMessage());
		}
		return json;
	}
	/*
	 * 
	 * */
	public String parseFetchAppointmentDetails(List<AppointmentDetails> appointments) {
		Gson gson = null;
		AppointmentDetailsJson appointmentJson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			Meta meta = new Meta();
			meta.setField("recordId");
			meta.setSort(Constants.SORT_ASC);
			meta.setTotal(appointments.size());
			appointmentJson = new AppointmentDetailsJson();
			appointmentJson.setData(appointments);
			appointmentJson.setMeta(meta);
			json = gson.toJson(appointmentJson);
		}
		catch(Exception e) {
			LOGGER.error("Exception in parseFetchAppointmentDetails :: "+e.getMessage());
		}
		return json;
	}
	/*
	 * 
	 * */
	public String createUnavailableMessage(boolean isStaffFree,String from,String to) {
		String json = null;
		Gson gson = null;
		UnavailableMessageJson UnavailableMessageJson = null;
		try {
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			UnavailableMessageJson = new UnavailableMessageJson();
			if(!isStaffFree) {
				UnavailableMessageJson.setFrom(from);
				UnavailableMessageJson.setTo(to);
				UnavailableMessageJson.setFree(isStaffFree);	
			}else {
				UnavailableMessageJson.setFree(isStaffFree);	
			}
			json = gson.toJson(UnavailableMessageJson);
		}catch(Exception e) {
			LOGGER.error("Exception in createUnavailableMessage :: "+e.getMessage());
		}
		return json;
	}

}
