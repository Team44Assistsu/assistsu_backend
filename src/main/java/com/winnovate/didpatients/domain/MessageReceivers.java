package com.winnovate.didpatients.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@Table(name = "MESSAGE_RECEIVERS")
public class MessageReceivers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MSG_RCVR_ID")
	private Integer messageReceiversId;
	
	@OneToMany
	@JoinColumn(name = "MSG_ID")
	private Message message;
	
	@OneToMany
	@JoinColumn(name = "ALTER_ID")
	private Integer receiver;
	
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

	public Integer getReceiver() {
		return receiver;
	}

	public void setReceiver(Integer receiver) {
		this.receiver = receiver;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
