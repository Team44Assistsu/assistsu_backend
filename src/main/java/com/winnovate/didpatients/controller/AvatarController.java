package com.winnovate.didpatients.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.AlterRequest;
import com.winnovate.didpatients.model.ChangeAlterRequest;
import com.winnovate.didpatients.response.AlterResponse;
import com.winnovate.didpatients.service.AlterService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class AvatarController {

	@Autowired
	AlterService service;

	@PostMapping("/saveAlter")
	public ResponseEntity<AlterResponse> createAlter(@RequestBody AlterRequest request) {

		AlterResponse alter = service.saveAlter(request);

		return new ResponseEntity<>(alter, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/getAlters")
	public ResponseEntity<List<AlterResponse>> getAlters(@RequestHeader("patientId") int patientId) {

		List<AlterResponse> alters = service.getAlerts(patientId);

		return new ResponseEntity<>(alters, HttpStatusCode.valueOf(200));
	}
	
	
	@GetMapping("/getAlter")
	public ResponseEntity<AlterResponse> getAlter(@RequestHeader("patientId") int patientId, @RequestHeader("alterId") int alterId) {

		AlterResponse alter = service.getAlterDetails(patientId, alterId);

		return new ResponseEntity<>(alter, HttpStatusCode.valueOf(200));
	}
	
	@PostMapping("/updateAlterPassword")
	public ResponseEntity<String> updateAlterPassword(@RequestBody ChangeAlterRequest request){
		return service.updateAlterPassword(request);
	}
	
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody ChangeAlterRequest request){
		return service.updatePassword(request);
	}
	
	@PostMapping("/updateAlterProfImg")
	public ResponseEntity<String> updateAlterProfImg(@RequestBody ChangeAlterRequest request){
		return service.updateAlterProfImg(request);
	}
	
	@PostMapping("/updateAlterDetails")
	public ResponseEntity<String> updateAlterDetails(@RequestBody ChangeAlterRequest request){
		return service.updateAlterDetails(request);
	}
	
	@GetMapping("/getAlterAccessDetails")
	public ResponseEntity<Object> getAltersCohostAccess(@RequestHeader("alterId") int alterId){
		return service.getAltersCohostAccessList(alterId);
	}
	
	@PostMapping("/updateAlterAccess")
	public ResponseEntity<String> updateAlterAccess(@RequestBody List<ChangeAlterRequest> request){
		return service.updateAlterAccess(request);
	}
}
