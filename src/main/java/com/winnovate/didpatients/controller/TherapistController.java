package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.TherapistRequest;
import com.winnovate.didpatients.service.TherapistService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TherapistController {

	@Autowired
	TherapistService service;

	@PostMapping("/saveTherapist")
	public ResponseEntity<Object> createPatientAccount(@RequestBody TherapistRequest request) {
		ResponseEntity<Object> response = service.saveTherapist(request);
		return response;
	}
	
	@GetMapping("/getPatients")
	public ResponseEntity<Object> getPatientDetails(@RequestHeader("therapistId") int patientId){
		return service.getPatientDetails(patientId);
	}
}
