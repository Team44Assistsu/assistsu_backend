package com.winnovate.didpatients.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.constants.Constants;
import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.model.PatientRequest;
import com.winnovate.didpatients.response.PatientResponse;

@Service
public class PatientService {

	@Autowired
	PatientDao patientDao;

	@Autowired
	LoginDao loginDao;

	public PatientResponse savePatient(PatientRequest request) {

		Login login = new Login();
		login.setPassword(request.getPassword());
		login.setUserName(request.getUserName());
		login = loginDao.save(login);
		
		Patient patient = new Patient();
		patient.setPatientName(request.getPatientName());
		patient.setPatientAge(request.getPatientAge());
		patient.setGender(request.getGender());
		patient.setEmail(request.getEmail());
		patient.setMobileNo(request.getMobileNumber());
		patient.setLogin(login);
		patient = patientDao.save(patient);
		
		return this.prepareResponse(patient);
	}
	
	private PatientResponse prepareResponse(Patient patient) {
		PatientResponse response = new PatientResponse();
		response.setPatientId(patient.getPatientId());
		response.setPatientName(patient.getPatientName());
		response.setPatientAge(patient.getPatientAge());
		response.setGender(patient.getGender());
		response.setEmail(patient.getEmail());
		response.setMobileNo(patient.getMobileNo());
		response.setLoginId(patient.getLogin().getLoginId());
		response.setUserName(patient.getLogin().getUserName());
		return response;
	}

	public Patient getPatientDetails(int patientId) {
		return patientDao.findByPatientId(patientId);
	}
}
