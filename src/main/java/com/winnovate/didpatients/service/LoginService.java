package com.winnovate.didpatients.service;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.dao.TherapistDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.domain.Therapist;
import com.winnovate.didpatients.model.AlterLoginRequest;
import com.winnovate.didpatients.model.LoginRequest;
import com.winnovate.didpatients.response.LoginResponse;

@Service
public class LoginService {

	private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

	@Autowired
	LoginDao loginDao;

	@Autowired
	AlterDao alterDao;

	@Autowired
	EmailSenderService service;

	@Autowired
	PatientDao patientDao;

	@Autowired
	TherapistDao therapistDao;

	public ResponseEntity<LoginResponse> validateUser(LoginRequest loginRequest) {

		Login login = loginDao.findByEmail(loginRequest.getEmail());
		LoginResponse response = new LoginResponse();
		if (login != null) {
			if (login.getPassword().equals(loginRequest.getPassword())) {
				response.setValid(true);
				response.setLoginStatus("successfully logged in");
				if (login.getPatient() != null) {
					response.setPatientId(login.getPatient().getPatientId());
				} else {
					response.setTherapistId(login.getTherpaist().getTherapistId());
				}
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			} else {
				response.setValid(false);
				response.setLoginStatus("Invalid OTP");
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
			}

		} else {
			response.setValid(false);
			response.setLoginStatus("Email not registered.");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
		}
	}

	public ResponseEntity<LoginResponse> validateAlter(AlterLoginRequest alterLoginRequest) {
		Optional<Alter> alter = alterDao.findById(alterLoginRequest.getAlterId());
		LoginResponse response = new LoginResponse();
		if (alter.isPresent()) {
			if (alter.get().getPin() == alterLoginRequest.getPin()) {
				response.setValid(true);
				response.setLoginStatus("alter successfully logged in");
				response.setPatientId(alter.get().getPatient().getPatientId());
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			} else {
				response.setValid(false);
				response.setLoginStatus("Invalid alter pin");
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
			}

		} else {
			response.setValid(false);
			response.setLoginStatus("Invalid alter");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
		}
	}

	public ResponseEntity<?> sendOTP(String toEmail) {
		Patient patient = patientDao.findByEmail(toEmail);
		String randomString = generateRandomString(16);
		boolean canSendMail = false;

		if (patient != null) {
			patient.getLogin().setPassword(randomString);
			canSendMail = true;
			patientDao.save(patient);
		}

		Therapist therapist = therapistDao.findByEmail(toEmail);
		if (therapist != null) {
			therapist.getLogin().setPassword(randomString);
			canSendMail = true;
			therapistDao.save(therapist);
		}

		if (canSendMail) {
			service.sendEmail(toEmail, "ASSISTU Login", "Your OTP is :  " + randomString);
			return new ResponseEntity<>("OTP has sent to your mail.", HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>("Email does not exists.", HttpStatusCode.valueOf(200));
		}
	}

	public static String generateRandomString(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
			char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}
}
