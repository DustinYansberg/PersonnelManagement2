package com.personnelmgmt2.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*") // TODO Cross-Origin security
public class AccountController {

	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${spring.datasource.url}/Accounts") private String baseUrl;
	
	private ResponseEntity<Object> sendRestTemplateExchange(Object body, String url, HttpMethod method) {
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
		HttpEntity<Object> entity = new HttpEntity<>(body, headers);
		return temp.exchange(url, method, entity, Object.class);
	}
	
	private ResponseEntity<Object> processError(Exception e) {
		//	TODO There is probably a way to formally extract error code from exception message
		int errorCode = Integer.parseInt(e.getMessage().substring(0, 3));
		System.out.println("\n\nEXCEPTION:\n" + errorCode);
		return ResponseEntity.status(HttpStatus.valueOf(errorCode)).header("Error", "SCIM Error " + errorCode)
				.body("An error occurred when sending the request to SCIM:\n" + e);
	}
	
	
	@GetMapping
	public ResponseEntity<Object> getAllAccounts() {
		try {
			return sendRestTemplateExchange(null, baseUrl, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Object> getAccountById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAccountById(@PathVariable String id) {
		//	SCIM DELETE does not return anything
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.DELETE);
		} catch(Exception e) {return processError(e);}
	}
}
