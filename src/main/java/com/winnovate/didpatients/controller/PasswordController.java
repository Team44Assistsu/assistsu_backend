package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.service.PasswordService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PasswordController {

	@Autowired
	PasswordService service;

	// This API is used to reset the password of the user
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(@RequestHeader("toEmail") String toEmail) {
		return service.resetPassword(toEmail);
	}
}
