package com.winnovate.didpatients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.MessageRequest;
import com.winnovate.didpatients.response.Message;
import com.winnovate.didpatients.response.MessageResponse;
import com.winnovate.didpatients.service.MessageService;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
public class MessageController {

	@Autowired
	MessageService service;

	@PostMapping("/sendMessage")
	public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequest request) {

		MessageResponse messageResponse = service.sendMessage(request);

		return new ResponseEntity<>(messageResponse, HttpStatusCode.valueOf(200));

	}
	
	@GetMapping("/viewMessage")
	public ResponseEntity<Message> sendMessage(@RequestHeader("messageId") Integer messageId, @RequestHeader("alterId") Integer alterId) {

		Message messageResponse = service.viewMessage(messageId, alterId);

		return new ResponseEntity<>(messageResponse, HttpStatusCode.valueOf(200));

	}
	
	@GetMapping("/messages")
	public ResponseEntity<List<Message>> getAllMessages(@RequestHeader("receiverId") Integer receiverId) {

		List<Message> messageResponse = service.getAllMessages(receiverId);

		return new ResponseEntity<>(messageResponse, HttpStatusCode.valueOf(200));

	}
}
