package com.sensedia.register.controller;

import java.util.Random;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sensedia.register.response.RegistrationResponse;

@RestController("/registrations")
public class RegistrationController {
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<RegistrationResponse> getRegistration(@RequestParam(name = "document") String document) {
		int randomScore = new Random().nextInt(11) * 100;
		return ResponseEntity.ok(new RegistrationResponse(randomScore));
	}

}
