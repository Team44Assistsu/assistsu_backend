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

	@GetMapping("/get_profile_images")
	@ResponseBody
	public ResponseEntity<List<ProfileImage>> getProfileImages() {
		return ResponseEntity.ok().body(service.getProfileImages());
	}

	@GetMapping("/get_profile_image")
	@ResponseBody
	public ResponseEntity<InputStream> getImageDynamicType(@RequestParam("image_id") int imageId) throws IOException {
		ClassPathResource resource = new ClassPathResource("/profile_images");
		InputStream in = resource.getInputStream();
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(in);
	}
}
