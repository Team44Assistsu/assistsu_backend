package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnovate.didpatients.domain.Login;

public interface LoginDao extends JpaRepository<Login, Integer>{

	/**
	 * This method finds a Login object by email.
	 *
	 * @param email the email to search for
	 * @return the Login object with the given email, or null if not found
	 */
	public Login findByEmail(String email);

}
