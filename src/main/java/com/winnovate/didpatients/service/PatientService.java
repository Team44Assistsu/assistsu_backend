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

	/**
	 * This method is used to save patient details. It validates whether the user is
	 * already registered or not. If the user is not registered, it saves the
	 * patient details and returns a response with HTTP status code 201. If the user
	 * is already registered, it returns a response with HTTP status code 500.
	 *
	 * @param request a PatientRequest object containing patient details
	 * @return a ResponseEntity object containing a PatientResponse object and HTTP
	 *         status code
	 */
	public ResponseEntity<Object> savePatient(PatientRequest request) {

		boolean isUserExisting = this.validateUser(request);

		if (!isUserExisting) {
			Login login = new Login();
			login.setEmail(request.getEmail());
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
			return new ResponseEntity<>("Email already registered", HttpStatusCode.valueOf(500));
		}
	}

	/**
	 * This method is used to validate whether the user is already registered or
	 * not.
	 *
	 * @param request a PatientRequest object containing patient details
	 * @return a boolean value indicating whether the user is already registered or
	 *         not
	 */

	private boolean validateUser(PatientRequest request) {
		Login login = loginDao.findByEmail(request.getEmail());
		if (login != null) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to prepare a PatientResponse object from the given Patient object.
	 *
	 * @param patient a Patient object containing patient details
	 * @return a PatientResponse object containing patient details
	 */
	private PatientResponse prepareResponse(Patient patient) {
		PatientResponse response = new PatientResponse();
		response.setPatientId(patient.getPatientId());
		response.setPatientName(patient.getPatientName());
		response.setPatientAge(patient.getPatientAge());
		response.setGender(patient.getGender());
		response.setEmail(patient.getEmail());
		response.setMobileNo(patient.getMobileNo());
		response.setLoginId(patient.getLogin().getLoginId());
		response.setUserName(patient.getLogin().getEmail());
		return response;
	}

	public ResponseEntity<Object> getPatientDetails(int patientId) {
		Patient patient = patientDao.findByPatientId(patientId);
		if (patient != null) {
			PatientResponse patientResponse = this.prepareResponse(patient);
			int profImgKey = patient.getAlters().stream().filter(alter -> alter.isHost()).findFirst().get()
					.getProfImgKey();
			if (profImgKey != 0) {
				patientResponse.setProfImgKey(profImgKey);
			}
			return new ResponseEntity<>(patientResponse, HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>("Patient Id not found", HttpStatusCode.valueOf(404));
		}
	}
}
