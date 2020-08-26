package org.net.erp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.net.erp.util.Constants;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "schedule_tbl")
public class Schedule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int scheduleId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master organization;
	
	@NotNull(message = Constants.SCHEDULE_DATE)
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(name = "SCHEDULE_DATE")
	private Date scheduleDate;
	
	@Column(name = "SCHEDULE_TIME_TO")
	private String scheduleTo;
	
	@Column(name = "SCHEDULE_TIME_FROM")
	private String scheduleFrom;
	
	@Column(name = "SCHEDULE_WITH")
	private String scheduleWith;
	
	@Column(name = "SCHEDULE_NOTES")
	private String scheduleNotes;
	
	@Column(name = "SCHEDULE_TOPIC")
	private String scheduleTopic;

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Master getOrganization() {
		return organization;
	}

	public void setOrganization(Master organization) {
		this.organization = organization;
	}

	public Date getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(Date scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getScheduleTo() {
		return scheduleTo;
	}

	public void setScheduleTo(String scheduleTo) {
		this.scheduleTo = scheduleTo;
	}

	public String getScheduleFrom() {
		return scheduleFrom;
	}

	public void setScheduleFrom(String scheduleFrom) {
		this.scheduleFrom = scheduleFrom;
	}

	public String getScheduleWith() {
		return scheduleWith;
	}

	public void setScheduleWith(String scheduleWith) {
		this.scheduleWith = scheduleWith;
	}

	public String getScheduleNotes() {
		return scheduleNotes;
	}

	public void setScheduleNotes(String scheduleNotes) {
		this.scheduleNotes = scheduleNotes;
	}

	public String getScheduleTopic() {
		return scheduleTopic;
	}

	public void setScheduleTopic(String scheduleTopic) {
		this.scheduleTopic = scheduleTopic;
	}
	
}
