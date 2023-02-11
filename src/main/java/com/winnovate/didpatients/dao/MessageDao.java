package com.winnovate.didpatients.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winnovate.didpatients.domain.Message;

@Repository
public interface MessageDao extends JpaRepository<Message, Integer> {

}
