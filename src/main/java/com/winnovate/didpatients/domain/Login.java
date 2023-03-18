package com.winnovate.didpatients.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LoginDetails")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOGINID")
	Integer loginId;

	@Column(name = "USERNAME")
	String userName;

	@Column(name = "PASSWORD")
	String password;

	@OneToOne(mappedBy="login")
    Patient patient;
	
	@OneToOne(mappedBy="loginDetails")
    Therapist therpaist;
	
	@Column(name = "NEW_LOGIN")
	boolean newLogin;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginId() {
		return loginId;
	}

	public void setLoginId(Integer loginId) {
		this.loginId = loginId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Therapist getTherpaist() {
		return therpaist;
	}

	public void setTherpaist(Therapist therpaist) {
		this.therpaist = therpaist;
	}

	public boolean isNewLogin() {
		return newLogin;
	}

	public void setNewLogin(boolean newLogin) {
		this.newLogin = newLogin;
	}
}
