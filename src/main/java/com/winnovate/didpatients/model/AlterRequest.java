package com.winnovate.didpatients.model;

public class AlterRequest {

	private String alterName;

	private int alterAge;

	private String alterGender;

	private String description;

	private int patientId;
	
	private int profImgKey;
	
	private int pin;
	
	private boolean isHost;
	
	private boolean isCohost;

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

	public int getProfImgKey() {
		return profImgKey;
	}

	public void setProfImgKey(int profImgKey) {
		this.profImgKey = profImgKey;
	}
	
	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public boolean isHost() {
		return isHost;
	}

	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}

	public boolean isCohost() {
		return isCohost;
	}

	public void setCohost(boolean isCohost) {
		this.isCohost = isCohost;
	}
}
