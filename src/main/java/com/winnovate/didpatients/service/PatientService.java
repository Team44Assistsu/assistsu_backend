package com.winnovate.didpatients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

	public ResponseEntity<Object> savePatient(PatientRequest request) {

		boolean isUserExisting = this.validateUser(request);

		if (!isUserExisting) {
			Login login = new Login();
			login.setPassword(request.getPassword());
			login.setUserName(request.getUserName());
			login.setNewLogin(false);
			login = loginDao.save(login);
			Patient patient = new Patient();
			patient.setPatientName(request.getPatientName());
			patient.setPatientAge(request.getPatientAge());
			patient.setGender(request.getGender());
			patient.setEmail(request.getEmail());
			patient.setMobileNo(request.getMobileNumber());
			patient.setLogin(login);
			patient = patientDao.save(patient);
			PatientResponse response = this.prepareResponse(patient);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));
		} else {
			return new ResponseEntity<>("User Id already exists" , HttpStatusCode.valueOf(500));
		}
	}

	private boolean validateUser(PatientRequest request) {
		Login login = loginDao.findByUserName(request.getUserName());
		if (login != null) {
			return true;
		}
		return false;
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

	public ResponseEntity<Object> getPatientDetails(int patientId) {
		Patient patient = patientDao.findByPatientId(patientId);
		if (patient != null) {
			PatientResponse patientResponse = this.prepareResponse(patient);
			int profImgKey = patient.getAlters().stream().filter(alter -> alter.isHost()).findFirst().get()
					.getProfImgKey();
			if(profImgKey != 0) {
				patientResponse.setProfImgKey(profImgKey);
			}
			return new ResponseEntity<>(patientResponse, HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>("Patient Id not found", HttpStatusCode.valueOf(404));
		}
	}
}
