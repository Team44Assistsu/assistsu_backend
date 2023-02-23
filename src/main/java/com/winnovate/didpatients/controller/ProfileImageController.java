package com.winnovate.didpatients.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ProfileImageController {

	@GetMapping("/get_profile_image")
	@ResponseBody
	public ResponseEntity<InputStreamResource> getImageDynamicType(@RequestParam("image_id") int imageId) throws IOException {
//		MediaType contentType = jpg ? MediaType.IMAGE_JPEG : MediaType.IMAGE_PNG;
//		InputStream in = jpg ? getClass().getResourceAsStream("resources/profile_images/HighLevelDiagram.jpg")
//				: getClass().getResourceAsStream("/com/baeldung/produceimage/image.png");
		ClassPathResource resource = new ClassPathResource("/profile_images/HighLevelDiagram.jpg");
		InputStream in = resource.getInputStream();
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(in));
	}
}
