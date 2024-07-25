package com.personnelmgmt2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.personnelmgmt2.models.Account;
import com.personnelmgmt2.repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired private AccountRepository repo;

	public ResponseEntity<Iterable<Account>> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

}
