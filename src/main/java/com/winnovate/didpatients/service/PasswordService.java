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

	public ResponseEntity<String> resetPassword(String toEmail) {
		Patient patient = patientDao.findByEmail(toEmail);
		String randomString = this.generateRandomString(16);
		boolean canSendMail = false;

		if (patient != null) {
			patient.getLogin().setPassword(randomString);
			patient.getLogin().setNewLogin(true);
			canSendMail = true;
			patientDao.save(patient);
		}

		Therapist therapist = therapistDao.findByEmail(toEmail);
		if (therapist != null) {
			therapist.getLogin().setPassword(randomString);
			therapist.getLogin().setNewLogin(true);
			canSendMail = true;
			therapistDao.save(therapist);
		}

		if (canSendMail) {
			service.sendEmail(toEmail, "ASSISTU : Reset Password", "Your New Password : " + randomString);
			return new ResponseEntity<>("New password has sent to your mail.", HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>("Email does not exists.", HttpStatusCode.valueOf(200));
		}
	}

	public String generateRandomString(int length) {
		int leftLimit = 97;
		int rightLimit = 122;
		int targetLength = length;
		Random random = new Random();

		return random.ints(leftLimit, rightLimit + 1).limit(targetLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
