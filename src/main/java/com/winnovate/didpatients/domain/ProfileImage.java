package com.winnovate.didpatients.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROFILE_IMAGE")
public class ProfileImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROF_IMG_ID")
	private Integer profImgId;

	@Column(name = "PROF_IMG_KEY")
	private int proImgKey;

	@Column(name = "PROF_IMG_URL")
	private int proImgURL;

	public Integer getProfImgId() {
		return profImgId;
	}

	public void setProfImgId(Integer profImgId) {
		this.profImgId = profImgId;
	}

	public int getProImgKey() {
		return proImgKey;
	}

	public void setProImgKey(int proImgKey) {
		this.proImgKey = proImgKey;
	}

	public int getProImgURL() {
		return proImgURL;
	}

	public void setProImgURL(int proImgURL) {
		this.proImgURL = proImgURL;
	}
}
