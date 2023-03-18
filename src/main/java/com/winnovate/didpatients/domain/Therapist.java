package com.winnovate.didpatients.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "THERAPIST")
public class Therapist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "THERAPIST_ID")
	private Integer therapistId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NO")
	private String phoneNo;

	@OneToOne
	@JoinColumn(name = "LOGIN_ID")
	Login loginDetails;

	public Integer getTherapistId() {
		return therapistId;
	}

	public void setTherapistId(Integer therapistId) {
		this.therapistId = therapistId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Login getLogin() {
		return loginDetails;
	}

	public void setLogin(Login loginDetails) {
		this.loginDetails = loginDetails;
	}
}
