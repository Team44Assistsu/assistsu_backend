package com.winnovate.didpatients.model;

import java.util.List;

public class MessageRequest {

	private Integer from;
	
	private String text;
	
	private List<Integer> recevierIds;
	
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

	public List<Integer> getRecevierIds() {
		return recevierIds;
	}

	public void setRecevierIds(List<Integer> recevierIds) {
		this.recevierIds = recevierIds;
	}
}
