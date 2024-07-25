package com.personnelmgmt2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personnelmgmt2.models.Account;
import com.personnelmgmt2.services.AccountService;

@RestController
@RequestMapping("/Accounts")
@CrossOrigin(origins = "*") // TODO Cross-Origin security
public class AccountController {
	@Autowired private AccountService service;
	
	@GetMapping
	public ResponseEntity<Iterable<Account>> getAllIdentities() {
		return service.getAllAccounts();
	}
}
