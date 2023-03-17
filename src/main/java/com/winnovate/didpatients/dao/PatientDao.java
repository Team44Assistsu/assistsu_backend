package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnovate.didpatients.domain.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient, Integer>{

	Patient findByPatientId(int patientId);
	
	Patient findByEmail(String email);
}
