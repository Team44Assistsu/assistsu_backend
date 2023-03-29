package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnovate.didpatients.domain.Login;

public interface LoginDao extends JpaRepository<Login, Integer>{

	public Login findByUserNameAndPassword(String username, String password);
	
	public Login findByUserName(String username);

	public Login findByEmail(String email);
}
