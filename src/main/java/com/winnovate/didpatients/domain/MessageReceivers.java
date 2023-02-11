package com.winnovate.didpatients.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MESSAGE_RECEIVERS")
public class MessageReceivers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MSG_RCVR_ID")
	private Integer messageReceiversId;
	
	@ManyToOne
	@JoinColumn(name = "MSG_ID")
	private Message message;
	
	@ManyToOne
	@JoinColumn(name = "TO_ALTER_ID")
	private Alter to;
	
	@Column(name = "IS_READ")
	private boolean isRead;

	public Integer getMessageReceiversId() {
		return messageReceiversId;
	}

	public void setMessageReceiversId(Integer messageReceiversId) {
		this.messageReceiversId = messageReceiversId;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Alter getTo() {
		return to;
	}

	public void setTo(Alter to) {
		this.to = to;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
