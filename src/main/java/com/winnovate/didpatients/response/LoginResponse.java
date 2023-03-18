package com.winnovate.didpatients.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LoginResponse {

	@JsonInclude(Include.NON_NULL)
	private boolean isValid;

	@JsonInclude(Include.NON_NULL)
	private String loginStatus;

	@JsonInclude(Include.NON_NULL)
	private Integer patientId;
	
	@JsonInclude(Include.NON_NULL)
	private Integer therapistId;

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getTherapistId() {
		return therapistId;
	}

	public void setTherapistId(Integer therapistId) {
		this.therapistId = therapistId;
	}
}
