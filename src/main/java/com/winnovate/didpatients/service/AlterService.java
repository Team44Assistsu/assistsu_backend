package com.winnovate.didpatients.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.model.AlterRequest;
import com.winnovate.didpatients.model.ChangeAlterRequest;
import com.winnovate.didpatients.response.AlterAccessResponse;
import com.winnovate.didpatients.response.AlterResponse;

@Service
public class AlterService {

	@Autowired
	AlterDao alterDao;

	@Autowired
	PatientDao patientDao;

	/**
	 * Create a new alter for a patient
	 * @param request AlterRequest containing alter details
	 * @return AlterResponse containing the saved alter
	 */
	public AlterResponse saveAlter(AlterRequest request) {
		
		// Find patient by ID
		Optional<Patient> patient = patientDao.findById(request.getPatientId());

		if (patient.isPresent()) {
			// Create new Alter object and set details from request
			Alter alter = new Alter();
			alter.setAlterName(request.getAlterName());
			alter.setAlterGender(request.getAlterGender());
			alter.setAlterAge(request.getAlterAge());
			alter.setPatient(patient.get());
			alter.setDescription(request.getDescription());
			alter.setProfImgKey(request.getProfImgKey());
			alter.setPin(request.getPin());
			alter.setHost(request.isHost());
			alter.setCohost(request.isCohost());
			// Save Alter object and prepare response
			alter = alterDao.save(alter);
			AlterResponse alterResonse = this.prepareAlterResponse(alter);
			return alterResonse;
		}
		// Return null if patient not found
		return null;
	}

	/**
	 * Prepare AlterResponse object from Alter object
	 * @param alter Alter object
	 * @return AlterResponse object
	 */
	AlterResponse prepareAlterResponse(Alter alter) {
		AlterResponse response = new AlterResponse();
		response.setAlterId(alter.getAlterId());
		response.setAlterName(alter.getAlterName());
		response.setAlterAge(alter.getAlterAge());
		response.setAlterGender(alter.getAlterGender());
		response.setProfImgKey(alter.getProfImgKey());
		response.setDescription(alter.getDescription());
		response.setHost(alter.isHost());
		response.setCohost(alter.isCohost());
		response.setPatientId(alter.getPatient().getPatientId());
		return response;
	}

	/**
	 * Get list of all Alters for a patient
	 * @param patientId ID of patient
	 * @return List of AlterResponse objects
	 */
	public List<AlterResponse> getAlerts(int patientId) {
		List<AlterResponse> altersResponse = new ArrayList<>();

		// Find patient by ID
		Optional<Patient> patient = patientDao.findById(patientId);

		if (patient.isPresent()) {
			// Find all Alters for patient and prepare AlterResponse objects
			List<Alter> alters = alterDao.findByPatient(patient.get());
			if (!alters.isEmpty()) {
				for (Alter alter : alters) {
					altersResponse.add(this.prepareAlterResponse(alter));
				}
			}
		}
		return altersResponse;
	}

	// Method to get Alter details based on patient id and alter id
	public AlterResponse getAlterDetails(int patientId, int alterId) {
		// Getting patient details based on patient id
		Optional<Patient> patient = patientDao.findById(patientId);
		AlterResponse alterResponse = new AlterResponse();

		// Checking if patient is present or not
		if (patient.isPresent()) {
			// Getting alter details based on patient id and alter id
			Alter alter = alterDao.findByPatientAndAlterId(patient.get(), alterId);
			if (alter != null) {
				alterResponse = this.prepareAlterResponse(alter);
			}
		}
		return alterResponse;
	}

	// Method to update Alter password
	public ResponseEntity<String> updateAlterPassword(ChangeAlterRequest request) {
		// Getting alter details based on alter id
		Optional<Alter> alter = alterDao.findById(request.getAlterId());
		if (alter.isPresent()) {
			// Checking if old password is matching or not
			if (alter.get().getPin() == request.getOldPin()) {
				alter.get().setPin(request.getNewPin());
				alterDao.save(alter.get());
				return new ResponseEntity<String>("password updated successfully.", HttpStatusCode.valueOf(200));
			} else {
				return new ResponseEntity<String>("old password is not matching.", HttpStatusCode.valueOf(401));
			}
		} else {
			return new ResponseEntity<String>("alter does not exists.", HttpStatusCode.valueOf(404));
		}
	}

	// Method to update Alter profile image
	public ResponseEntity<String> updateAlterProfImg(ChangeAlterRequest request) {
		// Getting alter details based on alter id
		Optional<Alter> alter = alterDao.findById(request.getAlterId());
		if (alter.isPresent()) {
			alter.get().setProfImgKey(request.getProfImgKey());
			alterDao.save(alter.get());
			return new ResponseEntity<String>("profile picture updated successfully.", HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<String>("alter does not exists.", HttpStatusCode.valueOf(404));
		}
	}

	/*
	 * This method updates the password of an alter.
	 * It takes a ChangeAlterRequest object as input which contains alterId, oldPassword and newPassword.
	 * It returns a ResponseEntity with success or error message.
	 */
	public ResponseEntity<String> updatePassword(ChangeAlterRequest request) {
		Optional<Alter> alter = alterDao.findById(request.getAlterId());
		if (alter.isPresent()) {
			if (alter.get().isHost()) {
				if (alter.get().getPatient().getLogin().getPassword().equals(request.getOldPassword())) {
					alter.get().getPatient().getLogin().setPassword(request.getNewPassword());
					alterDao.save(alter.get());
					return new ResponseEntity<String>("password updated successfully.", HttpStatusCode.valueOf(200));
				} else {
					return new ResponseEntity<String>("old password is not matching.", HttpStatusCode.valueOf(401));
				}
			} else {
				return new ResponseEntity<String>("alter does not have access to update the password.",
						HttpStatusCode.valueOf(401));
			}
		} else {
			return new ResponseEntity<String>("alter does not exists.", HttpStatusCode.valueOf(404));

		}
	}

	/*
	 * This method updates the profile details of an alter.
	 * It takes a ChangeAlterRequest object as input which contains alterId, alterName, description and access level.
	 * It returns a ResponseEntity with success or error message.
	 */
	public ResponseEntity<String> updateAlterDetails(ChangeAlterRequest request) {
		if(request.isHost() || request.isCohost() || request.isSelf()) {
			Optional<Alter> alter = alterDao.findById(request.getAlterId());
			if (alter.isPresent()) {
				if(request.getAlterName() != null) {
					alter.get().setAlterName(request.getAlterName());
				} 
				if(request.getDescription() != null) {
					alter.get().setDescription(request.getDescription());
				}
				alterDao.save(alter.get());
				return new ResponseEntity<String>("changes updated successfully.", HttpStatusCode.valueOf(200));
			} else {
				return new ResponseEntity<String>("alter does not exists.", HttpStatusCode.valueOf(404));
			}
		} else {
			return new ResponseEntity<String>("alter does not have access to update the profile details.",
					HttpStatusCode.valueOf(401));
		}
	}

	/*
	 * This method returns the list of alters for which the given alter has cohost access.
	 * It takes the alterId as input.
	 * It returns a ResponseEntity with list of AlterAccessResponse objects or an error message.
	 */
	public ResponseEntity<Object> getAltersCohostAccessList(int alterId) {
		List<AlterAccessResponse> alterResponses = new ArrayList<>();
		Optional<Alter> altr = alterDao.findById(alterId);
		if (altr.isPresent()) {
			if (altr.get().isHost()) {
				List<Alter> alters = altr.get().getPatient().getAlters();
				alters.remove(altr.get());
				for (Alter alter : alters) {
					AlterAccessResponse alterResponse = new AlterAccessResponse();
					alterResponse.setAlterId(alter.getAlterId());
					alterResponse.setAlterName(alter.getAlterName());
					alterResponse.setCohost(alter.isCohost());
					alterResponses.add(alterResponse);
				}
				return new ResponseEntity<>(alterResponses, HttpStatusCode.valueOf(200));
			} else {
				return new ResponseEntity<>("Alter does not have access to view the cohost access",
						HttpStatusCode.valueOf(401));
			}
		} else {
			return new ResponseEntity<>("Alter Id does not exists.", HttpStatusCode.valueOf(404));

		}
	}

	/**
	 * Update the cohost access for a list of alters
	 *
	 * @param request a list of ChangeAlterRequest objects containing the Alter IDs and their updated cohost access
	 * @return a ResponseEntity<String> indicating whether the operation was successful or not
	 */
	public ResponseEntity<String> updateAlterAccess(List<ChangeAlterRequest> request) {
		List<Integer> alterIds = new ArrayList<>();
		for (ChangeAlterRequest changeAlterRequest : request) {
			Optional<Alter> alter = alterDao.findById(changeAlterRequest.getAlterId());
			if (alter.isPresent()) {
				alter.get().setCohost(changeAlterRequest.isCohost());
				alterDao.save(alter.get());
			} else {
				alterIds.add(changeAlterRequest.getAlterId());
			}
		}
		if (alterIds.size() == 0) {
			return new ResponseEntity<String>("Updated the cohost access sucessfully", HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<String>("The alters Ids { " + alterIds + " } are not found",
					HttpStatusCode.valueOf(404));
		}
	} 

}
