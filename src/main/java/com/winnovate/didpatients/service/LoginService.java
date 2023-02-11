package com.winnovate.didpatients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.model.LoginRequest;

@Service
public class LoginService {

	@Autowired
	LoginDao loginDao;

	public ResponseEntity<String> validateUser(LoginRequest loginRequest) {
//		Login login = loginDao.findByUserNameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

		Login login = loginDao.findByUserName(loginRequest.getUsername());
		if (login != null) {
			if (login.getPassword().equals(loginRequest.getPassword())) {
				return new ResponseEntity<>("successfully logged in", HttpStatusCode.valueOf(200));
			} else {
				return new ResponseEntity<>("Invalid password", HttpStatusCode.valueOf(200));
			}

		} else {
			return new ResponseEntity<>("Invalid user", HttpStatusCode.valueOf(500));
		}
	}

}
