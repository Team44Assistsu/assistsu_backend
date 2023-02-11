package com.winnovate.didpatients.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Message_Receivers")
public class MessageReceivers {

	Integer messageReceiversId;
	
	Message message;
	
	Alter receiver;
	
	boolean isRead;
}
