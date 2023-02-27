package com.winnovate.didpatients.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROF_IMG_AVATAR_LINK")
public class ProfileAvatarLinking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROF_IMG_AVTR_LINK_ID")
	private Integer profImgAvtrLinkId;

	@Column(name = "PROF_IMG_ID")
	private int profImgId;

	@Column(name = "AVATAR_ID")
	private int avatarId;

	public Integer getProfImgAvtrLinkId() {
		return profImgAvtrLinkId;
	}

	public void setProfImgAvtrLinkId(Integer profImgAvtrLinkId) {
		this.profImgAvtrLinkId = profImgAvtrLinkId;
	}

	public int getProfImgId() {
		return profImgId;
	}

	public void setProfImgId(int profImgId) {
		this.profImgId = profImgId;
	}

	public int getAvatarId() {
		return avatarId;
	}

	public void setAvatarId(int avatarId) {
		this.avatarId = avatarId;
	}
}
