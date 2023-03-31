package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnovate.didpatients.domain.Login;

public interface LoginDao extends JpaRepository<Login, Integer>{

	public Login findByEmail(String email);
}
