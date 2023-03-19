package com.winnovate.didpatients.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AlterAccessResponse {

	@JsonInclude(Include.NON_NULL)
	private Integer alterId;

	@JsonInclude(Include.NON_NULL)
	private String alterName;

	@JsonInclude(Include.NON_NULL)
	private boolean isCohost;

	public Integer getAlterId() {
		return alterId;
	}

	public void setAlterId(Integer alterId) {
		this.alterId = alterId;
	}

	public String getAlterName() {
		return alterName;
	}

	public void setAlterName(String alterName) {
		this.alterName = alterName;
	}

	public boolean isCohost() {
		return isCohost;
	}

	public void setCohost(boolean isCohost) {
		this.isCohost = isCohost;
	}
}
