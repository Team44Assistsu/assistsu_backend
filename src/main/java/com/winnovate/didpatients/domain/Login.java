package com.winnovate.didpatients.domain;

import java.time.LocalDateTime;

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
	
	@Column(name = "EMAIL")
	String email;
	
	 @Column(name = "OTP_CREATED_DT")
	 LocalDateTime otpCreatedDt;

	@OneToOne(mappedBy="login")
    Patient patient;
	
	@OneToOne(mappedBy="loginDetails")
    Therapist therpaist;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getOtpCreatedDt() {
		return otpCreatedDt;
	}

	public void setOtpCreatedDt(LocalDateTime otpCreatedDt) {
		this.otpCreatedDt = otpCreatedDt;
	}
}
