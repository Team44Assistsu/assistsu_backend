package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.model.PatientRequest;
import com.winnovate.didpatients.response.PatientResponse;
import com.winnovate.didpatients.service.PatientService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class PatientController {

	@Autowired
	PatientService service;

	@PostMapping("/savePatient")
	public ResponseEntity<PatientResponse> createPatientAccount(@RequestBody PatientRequest request) {

		PatientResponse response = service.savePatient(request);
		return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getPatientDetails")
	public ResponseEntity<Patient> getPatientDetails(@RequestHeader("patientId") int patientId) {
		return new ResponseEntity<>(service.getPatientDetails(patientId), HttpStatusCode.valueOf(200));
	}
}
