package com.winnovate.didpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.model.AlterRequest;
import com.winnovate.didpatients.model.MessageRequest;
import com.winnovate.didpatients.response.AlterResponse;
import com.winnovate.didpatients.service.MessageService;

@RestController
public class MessageController {
	
	@Autowired
	MessageService service;

	@PostMapping("/sendMessage")
	public ResponseEntity<String> sendMessage(@RequestBody MessageRequest request) {

//		AlterResponse alter = service.sendMessage(request);

//		return new ResponseEntity<>(alter, HttpStatusCode.valueOf(200));
		
		return new ResponseEntity<String>("Message sent", HttpStatusCode.valueOf(200));
	}
}
