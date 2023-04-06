package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.AlterLoginRequest;
import com.winnovate.didpatients.model.LoginRequest;
import com.winnovate.didpatients.response.LoginResponse;
import com.winnovate.didpatients.service.LoginService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	/**
	 * This method is used to validate a patient login request.
	 * @param loginRequest The login request object.
	 * @return A response entity with the login response object.
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
		return loginService.validateUser(loginRequest);
	}
	
	/**
	 * This method is used to validate an alter login request.
	 * @param alterLoginRequest The alter login request object.
	 * @return A response entity with the login response object.
	 */
	@PostMapping("/validateAlter")
	public ResponseEntity<LoginResponse> login(@RequestBody AlterLoginRequest alterLoginRequest) {
		return loginService.validateAlter(alterLoginRequest);
	}
	
	/**
	 * This method is used to send an OTP to the specified email address.
	 * @param toEmail The email address to which the OTP will be sent.
	 * @return A response entity indicating the success or failure of the operation.
	 */
	@PostMapping("/sendOTP")
	public ResponseEntity<?> sendOTP(@RequestHeader("toEmail") String toEmail) {
		return loginService.sendOTP(toEmail);
	}
}
