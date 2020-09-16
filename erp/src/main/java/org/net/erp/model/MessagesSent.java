package org.net.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "messages_sent_tbl")
public class MessagesSent {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int messageId;
	
	@ManyToOne(fetch = FetchType.LAZY,optional=false)
	@JoinColumn(name = "master_id",nullable = false)
	private Master master;
	
	@Column(name = "MESSAGES_SENT_TO")
	private String messageSentTo;
	
	@Column(name = "MESSAGE_CONTENTS")
	private String messageContents;

	@Column(name = "MESSAGES_SENT")
	private int messageCount;
	
	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageSentTo() {
		return messageSentTo;
	}

	public void setMessageSentTo(String messageSentTo) {
		this.messageSentTo = messageSentTo;
	}

	public String getMessageContents() {
		return messageContents;
	}

	public void setMessageContents(String messageContents) {
		this.messageContents = messageContents;
	}

	public Master getMaster() {
		return master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}

	public int getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}
	
	
}
