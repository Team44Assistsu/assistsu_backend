package com.winnovate.didpatients.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Message;
import com.winnovate.didpatients.domain.MessageReceivers;

public interface MessageReceiversDao extends JpaRepository<MessageReceivers, Integer>{
	
	List<MessageReceivers> findByTo(Alter to);
	
	MessageReceivers findByToAndMessage(Alter to, Message msg);
}
