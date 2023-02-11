package com.winnovate.didpatients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.model.LoginRequest;
import com.winnovate.didpatients.model.LoginResponse;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;

	public ResponseEntity<LoginResponse> validateUser(LoginRequest loginRequest) {
//		Login login = loginDao.findByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

		Login login = loginDao.findByUserName(loginRequest.getUsername());
		LoginResponse response = new LoginResponse();
		if (login != null) {
			if (login.getPassword().equals(loginRequest.getPassword())) {
				response.setValid(true);
				response.setLoginStatus("successfully logged in");
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

}
