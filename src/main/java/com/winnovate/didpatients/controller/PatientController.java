package com.winnovate.didpatients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.model.PatientRequest;
import com.winnovate.didpatients.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	PatientService service;

	@PostMapping("/savePatient")
	public ResponseEntity<List<Object>> createPatientAccount(@RequestBody PatientRequest request) {

		List<Object> savedObjects = service.savePatient(request);

		return new ResponseEntity<>(savedObjects, HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getPatientDetails")
	public ResponseEntity<Patient> getPatientDetails(@RequestHeader("patientId") int patientId) {
		return new ResponseEntity<>(service.getPatientDetails(patientId), HttpStatusCode.valueOf(200));
	}
	

//	@PostMapping("/savePatient")
//	public ResponseEntity<Patient> createPatient() {
//		Patient patient = new Patient();
//		patient.setGender("MALE");
//		patient.setPatientName("DEvendra");
//		patient.setPatientAge(26);
//		Patient patient1 = patientDao.save(patient);
//		return new ResponseEntity<Patient>(patient1, HttpStatusCode.valueOf(200));
//	}
	
}
