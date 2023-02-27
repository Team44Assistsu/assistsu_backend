package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.AlterLoginRequest;
import com.winnovate.didpatients.model.LoginRequest;
import com.winnovate.didpatients.model.LoginResponse;
import com.winnovate.didpatients.service.LoginService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return loginService.validateUser(loginRequest);
	}
	
	@PostMapping("/validateAlter")
	public ResponseEntity<LoginResponse> login(@RequestBody AlterLoginRequest alterLoginRequest) {
		return loginService.validateAlter(alterLoginRequest);
	}
}
