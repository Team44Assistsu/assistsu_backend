package com.winnovate.didpatients.response;

import java.util.Date;

public class Message {

	Alter fromAlter;
	
	Alter toAlter;
	
	String msgText;
	
	boolean isRead;
	
	Date msgSentDate;
	
	Integer msgId;

	public Alter getFromAlter() {
		return fromAlter;
	}

	public void setFromAlter(Alter fromAlter) {
		this.fromAlter = fromAlter;
	}

	public Alter getToAlter() {
		return toAlter;
	}

	public void setToAlter(Alter toAlter) {
		this.toAlter = toAlter;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public Date getMsgSentDate() {
		return msgSentDate;
	}

	public void setMsgSentDate(Date msgSentDate) {
		this.msgSentDate = msgSentDate;
	}

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
}
