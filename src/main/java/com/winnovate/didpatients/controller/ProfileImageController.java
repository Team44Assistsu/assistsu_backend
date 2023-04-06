package com.winnovate.didpatients.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.winnovate.didpatients.domain.ProfileImage;
import com.winnovate.didpatients.service.ProfileImageService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProfileImageController {

	@Autowired
	ProfileImageService service;

	/**
	 * This method returns a list of all profile images stored in the database.
	 * 
	 * @return ResponseEntity<List<ProfileImage>> The HTTP response containing a list of ProfileImage objects.
	 */
	@GetMapping("/get_profile_images")
	@ResponseBody
	public ResponseEntity<List<ProfileImage>> getProfileImages() {
		return ResponseEntity.ok().body(service.getProfileImages());
	}

	/**
	 * This method retrieves the binary data of a specific profile image stored in the server's file system.
	 * 
	 * @param imageId The ID of the profile image to retrieve.
	 * @return ResponseEntity<InputStream> The HTTP response containing the binary data of the image in the response body.
	 * @throws IOException If there is an error accessing the server's file system to read the image file.
	 */
	@GetMapping("/get_profile_image")
	@ResponseBody
	public ResponseEntity<InputStream> getImageDynamicType(@RequestParam("image_id") int imageId) throws IOException {
		ClassPathResource resource = new ClassPathResource("/profile_images");
		InputStream in = resource.getInputStream();
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(in);
	}
}
