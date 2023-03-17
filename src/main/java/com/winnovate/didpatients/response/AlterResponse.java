package com.winnovate.didpatients.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class AlterResponse {

	@JsonInclude(Include.NON_NULL)
	private Integer alterId;

	@JsonInclude(Include.NON_NULL)
	private String alterName;

	@JsonInclude(Include.NON_NULL)
	private int alterAge;

	@JsonInclude(Include.NON_NULL)
	private String alterGender;

	@JsonInclude(Include.NON_NULL)
	private String description;

	@JsonInclude(Include.NON_NULL)
	private int patientId;

	@JsonInclude(Include.NON_NULL)
	private int profImgKey;

	@JsonInclude(Include.NON_NULL)
	private boolean isHost;

	@JsonInclude(Include.NON_NULL)
	private boolean isCohost;

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

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
}
