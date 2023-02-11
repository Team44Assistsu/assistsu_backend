package com.winnovate.didpatients.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MESSAGE_ID")
	private Integer messageId;
	
	@Column(name = "FROM_ALTER_ID")
	private Integer from;
	
	@Column(name = "TEXT")
	private String text;
	
	@Column(name = "DATE")
	private Date date;
	
	@OneToMany(mappedBy="message")
	private List<Integer> receivers;

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	
	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
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

	public List<Integer> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<Integer> receivers) {
		this.receivers = receivers;
	}
}
