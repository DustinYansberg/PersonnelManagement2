package com.personnelmgmt2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personnelmgmt2.models.Identity;
import com.personnelmgmt2.services.IdentityService;

@RestController
@RequestMapping("/Users") //	TODO Check how we use the baseURL with this
@CrossOrigin(origins = "") // TODO Cross-Origin security
public class IdentityController {
	@Autowired private IdentityService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Identity>> getAllIdentities() {
		return service.getAllIdentities();
	}
}
