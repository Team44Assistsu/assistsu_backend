package com.winnovate.didpatients.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALTER")
public class Alter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALTER_ID")
	private Integer alterId;

	@Column(name = "ALTER_NAME")
	private String alterName;

	@Column(name = "ALTER_AGE")
	private int alterAge;

	@Column(name = "ALTER_GENDER")
	private String alterGender;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PIN")
	private int pin;
	
	@Column(name = "PROF_IMG_KEY")
	private int profImgKey;
	
	@Column(name = "IS_HOST")
	private boolean isHost;
	
	@Column(name = "IS_CO_HOST")
	private boolean isCohost;

	@ManyToOne
	@JoinColumn(name = "PATIENT_ID")
	private Patient patient;

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

	public int getProfImgKey() {
		return profImgKey;
	}

	public void setProfImgKey(int profImgKey) {
		this.profImgKey = profImgKey;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getPin() {
		return pin;
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
