package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnovate.didpatients.domain.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer>{

	/*
	 * This method returns a Patient entity with the given patientId.
	 */
	Patient findByPatientId(int patientId);
	
	/*
	 * This method returns a Patient entity with the given email address.
	 */
	Patient findByEmail(String email);
}
