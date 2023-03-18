package com.winnovate.didpatients.model;

public class ChangeAlterRequest {
	
	String alterName;

	int alterId;
	
	int oldPin;
	
	int newPin;
	
	int profImgKey;
	
	String oldPassword;
	
	String newPassword;
	
	boolean isHost;
	
	boolean isCohost;
	
	boolean isSelf;
	
	String description;

	public int getAlterId() {
		return alterId;
	}

	public void setAlterId(int alterId) {
		this.alterId = alterId;
	}

	public int getOldPin() {
		return oldPin;
	}

	public void setOldPin(int oldPin) {
		this.oldPin = oldPin;
	}

	public int getNewPin() {
		return newPin;
	}

	public void setNewPin(int newPin) {
		this.newPin = newPin;
	}

	public int getProfImgKey() {
		return profImgKey;
	}

	public void setProfImgKey(int profImgKey) {
		this.profImgKey = profImgKey;
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

	public boolean isSelf() {
		return isSelf;
	}

	public void setSelf(boolean isSelf) {
		this.isSelf = isSelf;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getAlterName() {
		return alterName;
	}

	public void setAlterName(String alterName) {
		this.alterName = alterName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
