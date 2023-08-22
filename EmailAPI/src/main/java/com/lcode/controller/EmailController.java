package com.lcode.controller;

import javax.mail.NoSuchProviderException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lcode.model.EmailRequest;
import com.lcode.service.EmailService;

@RestController
public class EmailController {
	
	@Autowired
	EmailService emailService;
	

	
	
	@PostMapping("/send")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
	    try {
	        this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
	        return ResponseEntity.ok("Email sent successfully.");
	    } catch (AddressException e) {
	       
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email sending failed: " + e.getMessage());
	    }
	}

	
	@PostMapping("/attach")
	public ResponseEntity<?> sendAttach(@RequestBody EmailRequest request) {
		 try {
		this.emailService.sendAttach(request.getSubject(), request.getMessage(), request.getTo());
		return ResponseEntity.ok("Email sent successfully.");
		 } catch (AddressException e) {	       
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email sending failed: " + e.getMessage());
		    }
		
	}

}
