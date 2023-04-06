package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.PatientRequest;
import com.winnovate.didpatients.service.PatientService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PatientController {
	
	@Autowired
	PatientService service;

	/**
	 * Saves patient details
	 * @param request the patient details to save
	 * @return response entity indicating success or failure of the request
	 */
	@PostMapping("/savePatient")
	public ResponseEntity<Object> createPatientAccount(@RequestBody PatientRequest request) {

		ResponseEntity<Object> response = service.savePatient(request);
		return response;
	}

	/**
	 * Retrieves patient details by patient ID
	 * @param patientId the ID of the patient to retrieve
	 * @return response entity containing patient details or error message
	 */
	@GetMapping("/getPatientDetails")
	public ResponseEntity<Object> getPatientDetails(@RequestHeader("patientId") int patientId) {
		return service.getPatientDetails(patientId);
	}
}
