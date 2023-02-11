package com.winnovate.didpatients.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE")
public class Message {

	@Id
	Integer messageId;
	private String from;
	private String text;
	private Date date;
	 
}
