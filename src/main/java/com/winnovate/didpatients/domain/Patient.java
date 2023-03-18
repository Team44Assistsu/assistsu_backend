package com.winnovate.didpatients.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PatientDetails")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PATIENTID")
	private Integer patientId;

	@Column(name = "PATIENTNAME")
	private String patientName;

	@Column(name = "AGE")
	private int patientAge;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "MOBILENUMBER")
	private String mobileNo;

	@Column(name = "EMAILID")
	private String email;

	@OneToOne
	@JoinColumn(name = "LOGIN_ID")
	Login login;

	@OneToMany(mappedBy = "patient")
	List<Alter> alters;

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

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Alter> getAlters() {
		return alters;
	}

	public void setAlters(List<Alter> alters) {
		this.alters = alters;
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
}
