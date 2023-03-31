package com.winnovate.didpatients.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.dao.TherapistDao;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.domain.Therapist;
import com.winnovate.didpatients.model.TherapistRequest;
import com.winnovate.didpatients.response.PatientResponse;
import com.winnovate.didpatients.response.TherapistResponse;

@Service
public class TherapistService {

	@Autowired
	TherapistDao therapistDao;

	@Autowired
	LoginDao loginDao;
	
	@Autowired
	PatientDao patientDao;

	public ResponseEntity<Object> saveTherapist(TherapistRequest request) {
		boolean isUserExisting = this.validateUser(request);

		if (!isUserExisting) {
			Login login = new Login();
			login.setEmail(request.getEmail());
			login = loginDao.save(login);

			Therapist therapist = new Therapist();
			therapist.setName(request.getTherapistName());
			therapist.setEmail(request.getEmail());
			therapist.setPhoneNo(request.getMobileNumber());
			therapist.setLogin(login);

			therapist = therapistDao.save(therapist);

			TherapistResponse response = this.prepareResponse(therapist);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(201));
		} else {
			return new ResponseEntity<>(""
					+ "Email Id already exists", HttpStatusCode.valueOf(500));
		}
	}

	private TherapistResponse prepareResponse(Therapist therapist) {
		TherapistResponse response = new TherapistResponse();
		response.setEmail(therapist.getEmail());
		response.setPhoneNo(therapist.getPhoneNo());
		response.setTherapistName(therapist.getName());
		return response;
	}

	private boolean validateUser(TherapistRequest request) {
		Login login = loginDao.findByEmail(request.getEmail());
		if (login != null) {
			return true;
		}
		return false;
	}

	public ResponseEntity<Object> getPatientDetails(int therapistId) {
		TherapistResponse response = new TherapistResponse();
		List<PatientResponse> patientResponses = new ArrayList<>();
		Optional<Therapist> therapist = therapistDao.findById(therapistId);
		if (therapist.isPresent()) {
			List<Patient> patients = patientDao.findAll();
			for (Patient patient : patients) {
				PatientResponse patientResponse = new PatientResponse();
				Optional<Patient> patientDomain = patientDao.findById(patient.getPatientId());
				if (patientDomain.isPresent()) {
					patientResponse.setEmail(patientDomain.get().getEmail());
					patientResponse.setGender(patientDomain.get().getGender());
					patientResponse.setMobileNo(patientDomain.get().getMobileNo());
					patientResponse.setPatientName(patientDomain.get().getPatientName());
					patientResponse.setUserName(patientDomain.get().getLogin().getEmail());
					patientResponse.setPatientId(patientDomain.get().getPatientId());
					patientResponses.add(patientResponse);
				}
			}
			response.setPatients(patientResponses);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>("Therapist Id does not exists", HttpStatusCode.valueOf(500));
		}
	}
}
