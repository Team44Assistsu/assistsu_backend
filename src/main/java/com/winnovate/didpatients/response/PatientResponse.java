package com.winnovate.didpatients.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class PatientResponse {

	@JsonInclude(Include.NON_NULL)
	private Integer patientId;

	@JsonInclude(Include.NON_NULL)
	private String patientName;

	@JsonInclude(Include.NON_NULL)
	private int patientAge;

	@JsonInclude(Include.NON_NULL)
	private String gender;
	
	@JsonInclude(Include.NON_NULL)
	private String mobileNo;
	
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	private Integer loginId;

	@JsonInclude(Include.NON_NULL)
	private String userName;
	
	@JsonInclude(Include.NON_NULL)
	private List<AlterResponse> alters;

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AlterResponse> getAlters() {
		return alters;
	}

	public void setAlters(List<AlterResponse> alters) {
		this.alters = alters;
	}
}
