package com.winnovate.didpatients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;
	 /**
	  * Sends an email to the specified recipient with the given subject and body
	  * 
	  * @param toEmail The email address of the recipient
	  * @param subject The subject of the email
	  * @param body The body of the email
	  */
	public void sendEmail(String toEmail, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("avengers.44team@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
	}
	
	/**
	 * Sends an email containing an OTP to the specified recipient
	 * 
	 * @param toEmail The email address of the recipient
	 * @param OTP The One-Time Password to be sent in the email
	 */
	public void sendOTPEmail(String toEmail, String OTP) {
		String subject = "Your One-Time Password for Login";
		String body = "We have received a request to log in to your account. we require that you enter a One-Time Password (OTP) to complete the login process.\n"
				+ "\n"
				+ "Please use the following OTP to log in to your account:\n"
				+ "\n"
				+ "OTP: " + OTP  + " \n"
				+ "\n"
				+ "This OTP will expire in two minutes. If you did not request this login or if you have any concerns about your account security, please contact our customer support team.\n"
				+ "\n"
				+ "Thank you for using our service.\n"
				+ "\n"
				+ "Best regards,\n"
				+ "AssistsU";
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("noreply.assistsu@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);
	}
}
