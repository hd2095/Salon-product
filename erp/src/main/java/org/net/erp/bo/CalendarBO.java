package org.net.erp.bo;

import java.util.ArrayList;
import java.util.List;

import org.net.erp.json.CalendarJson;
import org.net.erp.model.Appointment;
import org.net.erp.util.Constants;
import org.net.erp.util.HibernateProxyTypeAdapter;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Configuration
public class CalendarBO extends BaseBO{
	/*
	 * 
	 * */
	public String parseFetchAppointment(List<Appointment> appointments) {
		Gson gson = null;
		String json = null;
		try {			
			GsonBuilder gb = new GsonBuilder();
			gb.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
			gson = gb.setPrettyPrinting().create();
			List<CalendarJson> eventList = new ArrayList<CalendarJson>();
			for(int i = 0;i<appointments.size();i++) {
				CalendarJson calendarJson = new CalendarJson();
				calendarJson.setDescription(appointments.get(i).getAppointmentNotes());
				String formatTime = appointments.get(i).getAppointmentStartTime();				
				String tokens[] = formatTime.split(Constants.COLON);		
				String appointmentDate = appointments.get(i).getAppointmentDate().toString().split(Constants.SPACE)[0];
				if(tokens[0].length() == 1) {
					tokens[0] = "0"+tokens[0];
				}
				String startTime = appointmentDate + "T" +tokens[0]+Constants.COLON+tokens[1].split(Constants.SPACE)[0]+Constants.COLON+"00"; 
				calendarJson.setStart(startTime);
				calendarJson.setTitle(appointments.get(i).getService().getServiceName());
				calendarJson.setClassName("fc-event-success");
				eventList.add(calendarJson);
			}
			json = gson.toJson(eventList);
	}catch(Exception e) {
		
	}
		return json;
	}
}
