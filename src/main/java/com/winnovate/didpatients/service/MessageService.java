package com.winnovate.didpatients.service;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.MessageDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Message;
import com.winnovate.didpatients.model.MessageRequest;

@Service
public class MessageService {

	@Autowired
	MessageDao messageDao;

	@Autowired
	AlterDao alterDao;

	public String sendMessage(MessageRequest request) {
		Optional<Alter> fromAlter = alterDao.findById(request.getFrom());
		if (fromAlter.isPresent()) {
			
			Message message = new Message();
			message.setFrom(fromAlter.get().getAlterId());
			message.setText(request.getText());
			message.setDate(new Date(new java.util.Date().getTime()));
			
			message = messageDao.save(message);
			for (Integer receviersId : request.getRecevierIds()) {
				Optional<Alter> toAlters = alterDao.findById(receviersId);
			}
		}
		return null;
	}

}
