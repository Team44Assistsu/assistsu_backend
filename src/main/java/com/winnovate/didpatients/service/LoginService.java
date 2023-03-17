package com.winnovate.didpatients.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.model.AlterLoginRequest;
import com.winnovate.didpatients.model.LoginRequest;
import com.winnovate.didpatients.response.LoginResponse;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;

	@Autowired
	AlterDao alterDao;

	public ResponseEntity<LoginResponse> validateUser(LoginRequest loginRequest) {

		Login login = loginDao.findByUserName(loginRequest.getUsername());
		LoginResponse response = new LoginResponse();
		if (login != null) {
			if (login.getPassword().equals(loginRequest.getPassword())) {
				response.setValid(true);
				if (login.isNewLogin()) {
					response.setLoginStatus("successfully logged in. Updated the password");
				} else {
					response.setLoginStatus("successfully logged in");
				}
				if (login.getPatient() != null) {
					response.setPatientId(login.getPatient().getPatientId());
				} else {
					response.setTherapistId(login.getTherpaist().getTherapistId());
				}
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			} else {
				response.setValid(false);
				response.setLoginStatus("Invalid password");
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
			}

		} else {
			response.setValid(false);
			response.setLoginStatus("Invalid user");
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

}
