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

@Service
public class PatientService {

	@Autowired
	PatientDao patientDao;

	@Autowired
	LoginDao loginDao;

	public List<Object> savePatient(PatientRequest request) {

		List<Object> savedObjects = new ArrayList<>();

		Login login = new Login();
		login.setPassword(request.getPassword());
		login.setUserName(request.getUserName());
		login = loginDao.save(login);
		
		Patient patient = new Patient();
		patient.setPatientName(request.getPatientName());
		patient.setPatientAge(request.getPatientAge());
		patient.setGender(request.getGender());
		patient.setLogin(login);
		patient = patientDao.save(patient);
		
		savedObjects.add(patient);
		savedObjects.add(login);
		return savedObjects;
	}
	
	public Patient getPatientDetails(int patientId) {
		return patientDao.findByPatientId(patientId);
	}
}
