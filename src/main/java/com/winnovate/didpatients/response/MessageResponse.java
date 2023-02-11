package com.winnovate.didpatients.response;

import java.util.Date;
import java.util.List;

public class MessageResponse {

	int msgId;
	
	String msgText;
	
	AlterResponse msgFrom;
	
	List<AlterResponse> msgTo;
	
	Date msgSentTime;
	
	boolean isRead = false;

	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsgText() {
		return msgText;
	}

	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}

	public AlterResponse getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(AlterResponse msgFrom) {
		this.msgFrom = msgFrom;
	}

	public List<AlterResponse> getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(List<AlterResponse> msgTo) {
		this.msgTo = msgTo;
	}

	public Date getMsgSentTime() {
		return msgSentTime;
	}

	public void setMsgSentTime(Date date) {
		this.msgSentTime = date;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
}
