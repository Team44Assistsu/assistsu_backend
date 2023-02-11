package com.winnovate.didpatients.response;

public class AlterResponse {

	private Integer alterId;

	private String alterName;

	private int alterAge;

	private String alterGender;

	private String description;

	private int patientId;

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

	public int getAlterAge() {
		return alterAge;
	}

	public void setAlterAge(int alterAge) {
		this.alterAge = alterAge;
	}

	public String getAlterGender() {
		return alterGender;
	}

	public void setAlterGender(String alterGender) {
		this.alterGender = alterGender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
}
