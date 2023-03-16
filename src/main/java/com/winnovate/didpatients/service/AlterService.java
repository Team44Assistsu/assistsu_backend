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
import com.winnovate.didpatients.response.AlterResponse;

@Service
public class AlterService {

	@Autowired
	AlterDao alterDao;

	@Autowired
	PatientDao patientDao;

	public AlterResponse saveAlter(AlterRequest request) {

		Optional<Patient> patient = patientDao.findById(request.getPatientId());

		if (patient.isPresent()) {
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
			alter = alterDao.save(alter);
			AlterResponse alterResonse = this.prepareAlterResponse(alter);
			return alterResonse;
		}
		return null;
	}

	AlterResponse prepareAlterResponse(Alter alter) {
		AlterResponse response = new AlterResponse();
		response.setAlterId(alter.getAlterId());
		response.setAlterName(alter.getAlterName());
		response.setAlterAge(alter.getAlterAge());
		response.setAlterGender(alter.getAlterGender());
		response.setProfImgKey(alter.getProfImgKey());
		response.setPatientId(alter.getPatient().getPatientId());
		return response;
	}

	public List<AlterResponse> getAlerts(int patientId) {
		List<AlterResponse> altersResponse = new ArrayList<>();

		Optional<Patient> patient = patientDao.findById(patientId);

		if (patient.isPresent()) {
			List<Alter> alters = alterDao.findByPatient(patient.get());
			if (!alters.isEmpty()) {
				for (Alter alter : alters) {
					altersResponse.add(this.prepareAlterResponse(alter));
				}
			}
		}
		return altersResponse;
	}

	public AlterResponse getAlterDetails(int patientId, int alterId) {
		Optional<Patient> patient = patientDao.findById(patientId);
		AlterResponse alterResponse = new AlterResponse();

		if (patient.isPresent()) {
			Alter alter = alterDao.findByPatientAndAlterId(patient.get(), alterId);
			if (alter != null) {
				alterResponse = this.prepareAlterResponse(alter);
			}
		}
		return alterResponse;
	}
	
	public ResponseEntity<String> updateAlterPassword(ChangeAlterRequest request) {
		Optional<Alter> alter = alterDao.findById(request.getAlterId());
		if(alter.isPresent()) {
			if(alter.get().getPin() == request.getOldPin()) {
				alter.get().setPin(request.getNewPin());
				alterDao.save(alter.get());
				return new ResponseEntity<String>("Password updated successfully", HttpStatusCode.valueOf(200));
			} else {
				return new ResponseEntity<String>("Old password is not matching", HttpStatusCode.valueOf(401)); 
			}
		} else {
			return new ResponseEntity<String>("Alter does not exists", HttpStatusCode.valueOf(404)); 
		}
	}

}
