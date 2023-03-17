package com.winnovate.didpatients.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class TherapistResponse {

	@JsonInclude(Include.NON_NULL)
	private String therapistName;

	@JsonInclude(Include.NON_NULL)
	private String email;

	@JsonInclude(Include.NON_NULL)
	private String password;

	@JsonInclude(Include.NON_NULL)
	private String phoneNo;

	@JsonInclude(Include.NON_NULL)
	private String userName;
	
	@JsonInclude(Include.NON_NULL)
	private List<PatientResponse> patients;
	
	public String getTherapistName() {
		return therapistName;
	}

	public void setTherapistName(String therapistName) {
		this.therapistName = therapistName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<PatientResponse> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientResponse> patients) {
		this.patients = patients;
	}
}
