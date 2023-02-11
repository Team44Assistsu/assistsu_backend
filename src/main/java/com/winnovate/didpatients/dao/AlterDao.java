package com.winnovate.didpatients.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Patient;

@Repository
public interface AlterDao extends JpaRepository<Alter, Integer>{

	List<Alter> findByPatient(Patient patient);
	
	Alter findByPatientAndAlterId(Patient patient, int alterId);
}
