package com.winnovate.didpatients.controller;

import java.util.List;

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

/**
 * This class is a RestController that manages requests related to Avatar objects.
 * It exposes various endpoints that allow the client to create, retrieve, and update avatars.
 * @author devendra
 * @version 1.0
 *
 */
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class AvatarController {

	@Autowired
	AlterService service;

	/**
	 * Endpoint to create a new avatar.
	 * @param request An AlterRequest object that represents the new avatar to be created.
	 * @return A ResponseEntity containing the newly created AlterResponse object.
	 */
	@PostMapping("/saveAlter")
	public ResponseEntity<AlterResponse> createAlter(@RequestBody AlterRequest request) {

		AlterResponse alter = service.saveAlter(request);

		return new ResponseEntity<>(alter, HttpStatusCode.valueOf(200));
	}

	/**
	 * Endpoint to retrieve all avatars associated with a patient ID.
     * @param patientId An integer representing the ID of the patient whose avatars to retrieve.
     * @return A ResponseEntity containing a List of AlterResponse objects.
     */
	@GetMapping("/getAlters")
	public ResponseEntity<List<AlterResponse>> getAlters(@RequestHeader("patientId") int patientId) {

		List<AlterResponse> alters = service.getAlerts(patientId);

		return new ResponseEntity<>(alters, HttpStatusCode.valueOf(200));
	}
	
	/**
	 * Endpoint to retrieve details of a specific avatar associated with a patient ID and avatar ID.
	 * @param patientId An integer representing the ID of the patient whose avatar to retrieve.
	 * @param alterId An integer representing the ID of the avatar to retrieve.
	 * @return A ResponseEntity containing the AlterResponse object representing the requested avatar.
	 */
	@GetMapping("/getAlter")
	public ResponseEntity<AlterResponse> getAlter(@RequestHeader("patientId") int patientId, @RequestHeader("alterId") int alterId) {

		AlterResponse alter = service.getAlterDetails(patientId, alterId);

		return new ResponseEntity<>(alter, HttpStatusCode.valueOf(200));
	}
	
	/**
	 * Endpoint to update an avatar's password.
	 * @param request A ChangeAlterRequest object containing the new password for the avatar.
 	 * @return A ResponseEntity containing a success message.
	 */
	@PostMapping("/updateAlterPassword")
	public ResponseEntity<String> updateAlterPassword(@RequestBody ChangeAlterRequest request){
		return service.updateAlterPassword(request);
	}
	/**
	 * Endpoint to update a patient's password.
	 * @param request A ChangeAlterRequest object containing the new password for the patient.
	 * @return A ResponseEntity containing a success message.
	 */
	@PostMapping("/updatePassword")
	public ResponseEntity<String> updatePassword(@RequestBody ChangeAlterRequest request){
		return service.updatePassword(request);
	}
	
	/**
	 * Endpoint to update an avatar's profile image.
	 * @param request A ChangeAlterRequest object containing the new profile image for the avatar.
	 * @return A ResponseEntity containing a success message.
	 */
	@PostMapping("/updateAlterProfImg")
	public ResponseEntity<String> updateAlterProfImg(@RequestBody ChangeAlterRequest request){
		return service.updateAlterProfImg(request);
	}
	
	/**
	 * Endpoint to update an avatar's details.
	 * @param request A ChangeAlterRequest object containing the new details for the avatar.
	 * @return A ResponseEntity containing a success message.
	 */
	@PostMapping("/updateAlterDetails")
	public ResponseEntity<String> updateAlterDetails(@RequestBody ChangeAlterRequest request){
		return service.updateAlterDetails(request);
	}
	
	/**
	 * Endpoint retrives the ater details 
	 * @param An integer representing the ID of the alter whose avatar to retrieve.
	 * @return
	 */
	@GetMapping("/getAlterAccessDetails")
	public ResponseEntity<Object> getAltersCohostAccess(@RequestHeader("alterId") int alterId){
		return service.getAltersCohostAccessList(alterId);
	}
	
	/**
	 * Endopint updates the cohost access for the alter. 
	 * @param request A ChangeAlterRequest object containing the new details for the avatar.
	 * @return
	 */
	@PostMapping("/updateAlterAccess")
	public ResponseEntity<String> updateAlterAccess(@RequestBody List<ChangeAlterRequest> request){
		return service.updateAlterAccess(request);
	}
}
