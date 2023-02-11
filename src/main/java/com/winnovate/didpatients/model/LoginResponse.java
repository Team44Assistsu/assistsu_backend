package com.winnovate.didpatients.model;

public class LoginResponse {
	
	private boolean isValid;
	
	private String loginStatus;
	
	private Integer patientId;
	
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
}
