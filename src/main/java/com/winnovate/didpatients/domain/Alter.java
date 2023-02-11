package com.winnovate.didpatients.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ALTER")
public class Alter {

	@Id
	Integer alterId;
	
	String alterName;
	
	int alterAge;
	
	String alterGender;
	
	String description;
	
	Patient patient;

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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
}
