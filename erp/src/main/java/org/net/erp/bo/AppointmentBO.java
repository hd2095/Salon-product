package org.net.erp.bo;

import java.util.List;

import org.net.erp.json.AppointmentDetailsJson;
import org.net.erp.json.AppointmentJson;
import org.net.erp.json.StaffUnavailableMessageJson;
import org.net.erp.model.Appointment;
import org.net.erp.model.AppointmentDetails;
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
			appointmentJson = new AppointmentJson();
			appointmentJson.setData(appointments);
			appointmentJson.setRecordsFiltered(appointments.size());
			appointmentJson.setRecordsTotal(appointments.size());
			json = gson.toJson(appointmentJson);
		}
		catch(Exception e) {
			System.out.println("Exception in parseFetchAppointment :: "+e.getMessage());
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
			System.out.println("Exception in parseFetchAppointmentDetails :: "+e.getMessage());
		}
		return json;
	}
	/*
	 * 
	 * */
	public String createStaffUnavailableMessage(boolean isStaffFree,String from,String to) {
		String json = null;
		Gson gson = null;
		StaffUnavailableMessageJson staffUnavailableMessageJson = null;
		try {
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			staffUnavailableMessageJson = new StaffUnavailableMessageJson();
			if(!isStaffFree) {
				staffUnavailableMessageJson.setFrom(from);
				staffUnavailableMessageJson.setTo(to);
				staffUnavailableMessageJson.setFree(isStaffFree);	
			}else {
				staffUnavailableMessageJson.setFree(isStaffFree);	
			}
			json = gson.toJson(staffUnavailableMessageJson);
		}catch(Exception e) {
			System.out.println("Exception in createStaffUnavailableMessage :: "+e.getMessage());
		}
		return json;
	}

}
