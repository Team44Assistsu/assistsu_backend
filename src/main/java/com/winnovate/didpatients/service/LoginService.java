package com.winnovate.didpatients.service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.winnovate.didpatients.dao.AlterDao;
import com.winnovate.didpatients.dao.LoginDao;
import com.winnovate.didpatients.dao.PatientDao;
import com.winnovate.didpatients.dao.TherapistDao;
import com.winnovate.didpatients.domain.Alter;
import com.winnovate.didpatients.domain.Login;
import com.winnovate.didpatients.domain.Patient;
import com.winnovate.didpatients.domain.Therapist;
import com.winnovate.didpatients.model.AlterLoginRequest;
import com.winnovate.didpatients.model.LoginRequest;
import com.winnovate.didpatients.response.LoginResponse;

@Service
public class LoginService {

	private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";

	@Autowired
	LoginDao loginDao;

	@Autowired
	AlterDao alterDao;

	@Autowired
	EmailSenderService service;

	@Autowired
	PatientDao patientDao;

	@Autowired
	TherapistDao therapistDao;

	/**
	 * Validates the login request of the user. If the email is registered and the
	 * OTP is not expired, the user can log in. If the password is correct and the
	 * user is a patient or a therapist, a success response with patient/therapist
	 * ID is returned. Otherwise, an error response is returned.
	 *
	 * @param loginRequest the login request containing the email and password
	 * @return a response entity with the login status and patient/therapist ID, if
	 *         successful.
	 */
	public ResponseEntity<LoginResponse> validateUser(LoginRequest loginRequest) {

		Login login = loginDao.findByEmail(loginRequest.getEmail());
		LoginResponse response = new LoginResponse();
		if (login != null) {
			if (login.getOtpCreatedDt() != null) {
				long duration = Duration.between(login.getOtpCreatedDt(), LocalDateTime.now()).toSeconds();
				if (duration < 120) {
					if (login.getPassword().equals(loginRequest.getPassword())) {
						response.setValid(true);
						response.setLoginStatus("successfully logged in");
						if (login.getPatient() != null) {
							response.setPatientId(login.getPatient().getPatientId());
						} else {
							response.setTherapistId(login.getTherpaist().getTherapistId());
						}
						return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
					} else {
						response.setValid(false);
						response.setLoginStatus("Invalid OTP");
						return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
					}
				} else {
					response.setValid(false);
					response.setLoginStatus("OTP is expired");
					return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
				}
			} else {
				response.setValid(false);
				response.setLoginStatus("OTP is not generated");
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
			}
		} else {
			response.setValid(false);
			response.setLoginStatus("Email not registered.");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
		}
	}

	/**
	 * This method validates an alter login request by checking if the given alter
	 * id and pin match the record in the Alter table. If the validation succeeds, a
	 * LoginResponse object with the status message "alter successfully logged in"
	 * is returned with HTTP 200 status. If the validation fails due to an invalid
	 * alter id or incorrect pin, a LoginResponse object with the status message
	 * "Invalid alter" or "Invalid alter pin" is returned with HTTP 401 status.
	 *
	 * @param alterLoginRequest An object containing the alter id and pin to be
	 *                          validated.
	 * @return A ResponseEntity object containing a LoginResponse object and an HTTP
	 *         status code indicating the result of the validation.
	 */
	public ResponseEntity<LoginResponse> validateAlter(AlterLoginRequest alterLoginRequest) {
		Optional<Alter> alter = alterDao.findById(alterLoginRequest.getAlterId());
		LoginResponse response = new LoginResponse();
		if (alter.isPresent()) {
			if (alter.get().getPin() == alterLoginRequest.getPin()) {
				response.setValid(true);
				response.setLoginStatus("alter successfully logged in");
				response.setPatientId(alter.get().getPatient().getPatientId());
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
			} else {
				response.setValid(false);
				response.setLoginStatus("Invalid alter pin");
				return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
			}

		} else {
			response.setValid(false);
			response.setLoginStatus("Invalid alter");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(401));
		}
	}

	/**
	 * Sends an OTP (One Time Password) to the specified email address.
	 * 
	 * @param toEmail the email address to send the OTP to
	 * @return a ResponseEntity containing a message indicating if the OTP was sent
	 *         or if the email does not exist
	 */
	public ResponseEntity<?> sendOTP(String toEmail) {
		Patient patient = patientDao.findByEmail(toEmail);
		String otp = generateRandomString(16);
		boolean canSendMail = false;

		if (patient != null) {
			patient.getLogin().setPassword(otp);
			patient.getLogin().setOtpCreatedDt(LocalDateTime.now());
			canSendMail = true;
			patientDao.save(patient);
		}

		Therapist therapist = therapistDao.findByEmail(toEmail);
		if (therapist != null) {
			therapist.getLogin().setPassword(otp);
			therapist.getLogin().setOtpCreatedDt(LocalDateTime.now());
			canSendMail = true;
			therapistDao.save(therapist);
		}

		if (canSendMail) {
			service.sendOTPEmail(toEmail, otp);
			return new ResponseEntity<>("OTP has sent to your mail.", HttpStatusCode.valueOf(200));
		} else {
			return new ResponseEntity<>("Email does not exists.", HttpStatusCode.valueOf(200));
		}
	}

	/**
	 * This method generates a random string of specified length and returns it. The
	 * generated string can contain upper and lowercase letters, digits, and special
	 * characters.
	 *
	 * @param length The length of the random string to be generated.
	 * @return A random string of specified length.
	 */
	public static String generateRandomString(int length) {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
			char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
			sb.append(randomChar);
		}

		return sb.toString();
	}
}
