package com.winnovate.didpatients.model;

public class ChangeAlterRequest {

	int alterId;
	
	int oldPin;
	
	int newPin;

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
}
