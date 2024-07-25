package com.personnelmgmt2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.personnelmgmt2.models.Identity;
import com.personnelmgmt2.repositories.IdentityRepository;

@Service
public class IdentityService {
	@Autowired private IdentityRepository repo;
	
	public ResponseEntity<Iterable<Identity>> getAllIdentities() {
		return ResponseEntity.status(200).header("Message", "Returned all identities")
				.body(repo.getAllIdentities());
	}
}
