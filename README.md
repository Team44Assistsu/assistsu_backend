## **AssistsU application**

#** LoginController **

This is a REST API Spring Boot application controller.
<br>
** Endpoints **

> Login
	<ul>
	  <li>URL: /login</li>
	  <li>Method: POST</li>
	  <li>Description: Validates user credentials.</li>
	  <li>Request Body: LoginRequest</li>
	  <li>Response: LoginResponse</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
<br>
> Validate Alter
	<ul>
	  <li>URL: /validateAlter</li>
	  <li>Method: POST</li>
	  <li>Description: Validates Alter credentials.</li>
	  <li>Request Body: AlterLoginRequest</li>
	  <li>Response: LoginResponse</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
<br>
>Send OTP
	<ul>
	  <li>URL: /sendOTP</li>
	  <li>Method: POST</li>
	  <li>Description: Sends OTP to the specified email.</li>
	  <li>Request Header: toEmail</li>
	  <li>Response: ResponseEntity<?></li>
	  <li>HTTP Status Code: 200</li>
	</ul>
<br>
>Dependencies
>>LoginService

#**AvatarController**
This is a REST API Spring Boot application controller.   
<br>
**Endpoints**  
> **Save Alter**
<ul>
  <li>URL: /saveAlter</li>
  <li>Method: POST</li>
  <li>Description: Creates a new Alter.</li>
  <li>Request Body: AlterRequest</li>
   <li>Response: AlterResponse</li>
  <li>HTTP Status Code: 200  </li>
</ul>
<br>
> **Get Alters**
  <ul>
    <li>URL: /getAlters</li>
    <li>Method: GET</li>
    <li>Description: Gets all Alters for a given patient.</li>
    <li>Request Header: patientId</li>
    <li>Response: List of AlterResponse</li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>
> **Get Alter**
  <ul>
    <li>URL: /getAlter</li>
    <li>Method: GET</li>
    <li>Description: Gets details of a specific Alter for a given patient.</li>
    <li>Request Headers: patientId, alterId</li>
    <li>Response: AlterResponse</li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>
> **Update Alter Password**
  <ul>
    <li>URL: /updateAlterPassword</li>
    <li>Method: POST</li>
    <li>Description: Updates the password of an Alter.</li>
    <li>Request Body: ChangeAlterRequest</li>
    <li>Response: String</li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>
> **Update Alter Profile Image**
  <ul>
    <li>URL: /updateAlterProfImg</li>
    <li>Method: POST</li>
    <li>Description: Updates the profile image of an Alter.</li>
    <li>Request Body: ChangeAlterRequest</li>
    <li>Response: String</li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>
> **Update Alter Details**
  <ul>
    <li>URL: /updateAlterDetails</li>
    <li>Method: POST</li>
    <li>Description: Updates details of an Alter.</li>
    <li>Request Body: ChangeAlterRequest</li>
    <li>Response: String</li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>
> **Get Alter Access Details**
  <ul>
    <li>URL: /getAlterAccessDetails</li>
    <li>Method: GET</li>
    <li>Description: Gets access details of an Alter.</li>
    <li>Request Header: alterId</li>
    <li>Response: Object</li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>
> **Update Alter Access**
  <ul>
    <li>URL: /updateAlterAccess</li>
    <li>Method: POST</li>
    <li>Description: Updates access details of an Alter.</li>
    <li>Request Body: List of ChangeAlterRequest</li>
    <li>Response: String</li>
    <li>HTTP Status Code: 200</li>
    
#**PatientController**

This is a REST API Spring Boot application controller.

**Endpoints**

>Save Patient
	<ul>
	  <li>URL: /savePatient</li>
	  <li>Method: POST</li>
	  <li>Description: Creates a new patient account.</li>
	  <li>Request Body: PatientRequest</li>
	  <li>Response: Object</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
<br>
>Get Patient Details
	<ul>
	  <li>URL: /getPatientDetails</li>
	  <li>Method: GET</li>
	  <li>Description: Gets details of a specific patient for a given patientId.</li>
	  <li>Request Headers: patientId</li>
	  <li>Response: Object</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
<br>
>Dependencies

>>PatientService


#**MessageController**

This is a REST API Spring Boot application controller for message related functionalities.

**Endpoints**
>sendMessage
	<ul>
	  <li>URL: /sendMessage</li>
	  <li>Method: POST</li>
	  <li>Description: Sends a message to a recipient.</li>
	  <li>Request Body: MessageRequest</li>
	  <li>Response: MessageResponse</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
>viewMessage
	<ul>
	  <li>URL: /viewMessage</li>
	  <li>Method: GET</li>
	  <li>Description: Gets details of a specific message for a given patient.</li>
	  <li>Request Headers: messageId, alterId</li>
	  <li>Response: Message</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
>getAllMessages
	<ul>
	  <li>URL: /messages</li>
	  <li>Method: GET</li>
	  <li>Description: Gets all messages for a given patient.</li>
	  <li>Request Header: receiverId</li>
	  <li>Response: List of Message</li>
	  <li>HTTP Status Code: 200</li>
	</ul>
#**TherapistController**

This is a REST API Spring Boot application controller.

**Endpoints**

> Save Therapist
  <ul>
    <li>URL: /saveTherapist</li>
    <li>Method: POST</li>
    <li>Description: Creates a new therapist account.</li>
    <li>Request Body: TherapistRequest</li>
    <li>Response: ResponseEntity<Object></li>
    <li>HTTP Status Code: 200</li>
  </ul>
 <br>
> Get Patients
  <ul>
    <li>URL: /getPatients</li>
    <li>Method: GET</li>
    <li>Description: Gets all patients for a given therapist.</li>
    <li>Request Header: therapistId</li>
    <li>Response: ResponseEntity<Object></li>
    <li>HTTP Status Code: 200</li>
  </ul>
  <br>


