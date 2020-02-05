package com.iknet.DemandeDePrestation.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ServiceMessagerie {
	
	@PostMapping("/envoieMail")
	public void postController(
	  @RequestBody CourierElectronique courier) {
		courier.envoyer();
	}
}
