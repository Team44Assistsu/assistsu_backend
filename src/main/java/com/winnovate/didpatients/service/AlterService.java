package com.winnovate.didpatients.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.model.AlterRequest;
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

}
