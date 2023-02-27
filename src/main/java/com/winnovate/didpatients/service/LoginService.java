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
import com.winnovate.didpatients.model.LoginResponse;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;

	@Autowired
	AlterDao alterDao;

	public ResponseEntity<LoginResponse> validateUser(LoginRequest loginRequest) {
//		Login login = loginDao.findByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

		Login login = loginDao.findByUserName(loginRequest.getUsername());
		LoginResponse response = new LoginResponse();
		if (login != null) {
			if (login.getPassword().equals(loginRequest.getPassword())) {
				response.setValid(true);
				response.setLoginStatus("successfully logged in");
				response.setPatientId(login.getPatient().getPatientId());
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			} else {
				response.setValid(false);
				response.setLoginStatus("Invalid password");
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			}

		} else {
			response.setValid(false);
			response.setLoginStatus("Invalid user");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
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
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			}

		} else {
			response.setValid(false);
			response.setLoginStatus("Invalid alter");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
		}
	}

}
