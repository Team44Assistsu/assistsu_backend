package com.winnovate.didpatients.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.dao.TherapistDao;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.domain.Therapist;

@Service
public class PasswordService {

	@Autowired
	EmailSenderService service;

	@Autowired
	PatientDao patientDao;

	@Autowired
	LoginDao loginDao;

	@Autowired
	TherapistDao therapistDao;

	// Method to reset a user's password
	public ResponseEntity<String> resetPassword(String toEmail) {
		// Check if user is a patient
		Patient patient = patientDao.findByEmail(toEmail);
		String randomString = this.generateRandomString(16);
		boolean canSendMail = false;

		// If user is a patient, update their password and set flag to allow sending of
		// email
		if (patient != null) {
			patient.getLogin().setPassword(randomString);
			canSendMail = true;
			patientDao.save(patient);
		}

		// Check if user is a therapist
		Therapist therapist = therapistDao.findByEmail(toEmail);
		if (therapist != null) {
			therapist.getLogin().setPassword(randomString);
			canSendMail = true;
			therapistDao.save(therapist);
		}

		// If user is either a patient or therapist, send email with new password
		if (canSendMail) {
			service.sendEmail(toEmail, "ASSISTU : Reset Password", "Your New Password : " + randomString);
			return new ResponseEntity<>("New password has sent to your mail.", HttpStatusCode.valueOf(200));
		} else {
			// If user is neither a patient nor therapist, return error message
			return new ResponseEntity<>("Email does not exists.", HttpStatusCode.valueOf(200));
		}
	}

	// Method to generate a random string of a specified length
	public String generateRandomString(int length) {
		int leftLimit = 97;
		int rightLimit = 122;
		int targetLength = length;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).limit(targetLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
