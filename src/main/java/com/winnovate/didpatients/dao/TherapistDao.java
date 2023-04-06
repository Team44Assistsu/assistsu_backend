package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnovate.didpatients.domain.Therapist;

public interface TherapistDao extends JpaRepository<Therapist, Integer> {

	/**
	 * This method finds the therapist by email.
	 *
	 * @param email email of the therapist to be found.
	 * @return therapist with the given email.
	 */
	Therapist findByEmail(String email);
}
