package com.winnovate.didpatients.domain;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MESSAGE_ID")
	private Integer messageId;
	
	@OneToOne
	@JoinColumn(name = "FROM_ALTER_ID")
	private Alter from;
	
	@Column(name = "TEXT")
	private String text;
	
	@Column(name = "DATE")
	private Date date;
	
	@OneToMany(mappedBy="message")
	private List<MessageReceivers> receivers;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Alter getFrom() {
		return from;
	}

	public void setFrom(Alter from) {
		this.from = from;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<MessageReceivers> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<MessageReceivers> receivers) {
		this.receivers = receivers;
	}
}
