package com.winnovate.didpatients.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.MessageDao;
import com.winnovate.didpatients.dao.MessageReceiversDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Message;
import com.winnovate.didpatients.domain.MessageReceivers;
import com.winnovate.didpatients.model.MessageRequest;
import com.winnovate.didpatients.response.AlterResponse;
import com.winnovate.didpatients.response.MessageResponse;

@Service
public class MessageService {

	@Autowired
	MessageDao messageDao;

	@Autowired
	AlterDao alterDao;

	@Autowired
	MessageReceiversDao messageReceiversDao;

	// Sends a message from a sender to a list of recipients
	public MessageResponse sendMessage(MessageRequest request) {

		MessageResponse response = new MessageResponse();

		// Get the sender of the message
		Optional<Alter> fromAlter = alterDao.findById(request.getFrom());

		// Check if the sender is valid
		if (fromAlter.isPresent()) {

			// Create a message object and set its properties
			Message message = new Message();
			message.setFrom(fromAlter.get());
			message.setText(request.getText());
			message.setDate(new Date(new java.util.Date().getTime()));
			message = messageDao.save(message);

			// Set the response object's properties
			response.setMsgFrom(this.prepareAlterResponse(fromAlter.get()));
			response.setMsgId(message.getMessageId());
			response.setMsgText(message.getText());
			response.setMsgSentTime(new java.util.Date(message.getDate().getTime()));

			List<MessageReceivers> receivers = new ArrayList<>();
			List<Alter> toAlters = new ArrayList<>();

			// Get the list of recipients and create a message receiver object for each of
			// them
			for (Integer receviersId : request.getRecevierIds()) {
				Optional<Alter> toAlter = alterDao.findById(receviersId);
				MessageReceivers receiver = new MessageReceivers();
				if (toAlter.isPresent()) {
					receiver.setTo(toAlter.get());
					receiver.setRead(false);
					receiver.setMessage(message);
				}
				toAlters.add(toAlter.get());
				receivers.add(receiver);
			}

			response.setMsgTo(prepareAlterResponse(toAlters));
			messageReceiversDao.saveAll(receivers);
		}

		return response;
	}

	// Prepare the AlterResponse object
	AlterResponse prepareAlterResponse(Alter alter) {
		AlterResponse response = new AlterResponse();
		response.setAlterId(alter.getAlterId());
		response.setAlterName(alter.getAlterName());
		response.setAlterAge(alter.getAlterAge());
		response.setAlterGender(alter.getAlterGender());
		response.setProfImgKey(alter.getProfImgKey());
		response.setPatientId(alter.getPatient().getPatientId());
		return response;
	}

	// Prepare the list of AlterResponse objects
	List<AlterResponse> prepareAlterResponse(List<Alter> alters) {
		List<AlterResponse> alterResponses = new ArrayList<>();

		for (Alter alter : alters) {
			AlterResponse response = new AlterResponse();
			response.setAlterId(alter.getAlterId());
			response.setAlterName(alter.getAlterName());
			response.setAlterAge(alter.getAlterAge());
			response.setAlterGender(alter.getAlterGender());
			response.setProfImgKey(alter.getProfImgKey());
			response.setPatientId(alter.getPatient().getPatientId());
			alterResponses.add(response);
		}
		return alterResponses;
	}

	/**
	 * This method retrieves all messages for a given receiver.
	 * 
	 * @param receiverId The ID of the Alter that the messages should be retrieved
	 *                   for.
	 * @return A list of Message objects for the given receiver.
	 */
	public List<com.winnovate.didpatients.response.Message> getAllMessages(Integer receiverId) {
		Optional<Alter> alter = alterDao.findById(receiverId);
		List<com.winnovate.didpatients.response.Message> messages = new ArrayList<>();
		if (alter.isPresent()) {
			List<MessageReceivers> messageReceivers = messageReceiversDao.findByTo(alter.get());
			for (MessageReceivers messageReceiver : messageReceivers) {
				com.winnovate.didpatients.response.Message message = new com.winnovate.didpatients.response.Message();
				message.setRead(messageReceiver.isRead());
				message.setMsgSentDate(messageReceiver.getMessage().getDate());
				com.winnovate.didpatients.response.Alter toAlter = new com.winnovate.didpatients.response.Alter();
				toAlter.setAlterId(messageReceiver.getTo().getAlterId());
				toAlter.setAlterName(messageReceiver.getTo().getAlterName());
				toAlter.setAlterGender(messageReceiver.getTo().getAlterGender());
				toAlter.setProfImgKey(messageReceiver.getTo().getProfImgKey());
				message.setToAlter(toAlter);

				com.winnovate.didpatients.response.Alter fromAlter = new com.winnovate.didpatients.response.Alter();
				fromAlter.setAlterId(messageReceiver.getMessage().getFrom().getAlterId());
				fromAlter.setAlterName(messageReceiver.getMessage().getFrom().getAlterName());
				fromAlter.setAlterGender(messageReceiver.getMessage().getFrom().getAlterGender());
				fromAlter.setProfImgKey(messageReceiver.getMessage().getFrom().getProfImgKey());
				message.setFromAlter(fromAlter);
				message.setMsgText(messageReceiver.getMessage().getText());
				message.setMsgId(messageReceiver.getMessage().getMessageId());
				messages.add(message);
			}
		}
		return messages;
	}

	/**
	 * This method retrieves a specific message for a given Alter.
	 * 
	 * @param messageId The ID of the message to be retrieved.
	 * @param alterId   The ID of the Alter that the message should be retrieved
	 *                  for.
	 * @return A Message object containing the requested message data.
	 */
	public com.winnovate.didpatients.response.Message viewMessage(Integer messageId, Integer alterId) {

		com.winnovate.didpatients.response.Message message = new com.winnovate.didpatients.response.Message();

		Optional<Message> sentMessage = messageDao.findById(messageId);

		Optional<Alter> toAlter = alterDao.findById(alterId);

		if (toAlter.isPresent() && sentMessage.isPresent()) {
			MessageReceivers msgReceiver = messageReceiversDao.findByToAndMessage(toAlter.get(), sentMessage.get());
			message.setMsgId(sentMessage.get().getMessageId());
			com.winnovate.didpatients.response.Alter fromAlter = new com.winnovate.didpatients.response.Alter();
			fromAlter.setAlterId(sentMessage.get().getFrom().getAlterId());
			fromAlter.setAlterName(sentMessage.get().getFrom().getAlterName());
			fromAlter.setAlterGender(sentMessage.get().getFrom().getAlterGender());
			fromAlter.setProfImgKey(sentMessage.get().getFrom().getProfImgKey());
			message.setFromAlter(fromAlter);
			message.setMsgText(sentMessage.get().getText());
			message.setMsgSentDate(sentMessage.get().getDate());
			message.setRead(true);
			msgReceiver.setRead(true);
			messageReceiversDao.save(msgReceiver);
		}
		return message;
	}
}
