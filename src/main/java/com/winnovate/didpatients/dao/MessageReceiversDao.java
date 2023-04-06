package com.winnovate.didpatients.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Message;
import com.winnovate.didpatients.domain.MessageReceivers;

public interface MessageReceiversDao extends JpaRepository<MessageReceivers, Integer>{
	
	/**
	 * This method finds all the message receivers for a given recipient.
	 * 
	 * @param to The recipient of the message.
	 * @return A list of MessageReceivers for the given recipient.
	 */
	List<MessageReceivers> findByTo(Alter to);

	/**
	 * This method finds a message receiver for a given recipient and message.
	 * 
	 * @param to The recipient of the message.
	 * @param msg The message.
	 * @return The MessageReceivers entity that matches the given recipient and message.
	 */
	MessageReceivers findByToAndMessage(Alter to, Message msg);

}
