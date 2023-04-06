package com.winnovate.didpatients.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Patient;

@Repository
public interface AlterDao extends JpaRepository<Alter, Integer>{
	
	/**
	 * Finds a list of alters associated with a given patient
	 * @param patient the patient entity to retrieve alters for
	 * @return a list of Alter entities associated with the patient
	 */
	List<Alter> findByPatient(Patient patient);

	/**
	 * Finds an Alter entity with a given alterId that is associated with a given patient
	 * @param patient the patient entity that the alter is associated with
	 * @param alterId the id of the alter entity to retrieve
	 * @return the Alter entity associated with the patient and alterId, or null if not found
	 */
	Alter findByPatientAndAlterId(Patient patient, int alterId);

}
