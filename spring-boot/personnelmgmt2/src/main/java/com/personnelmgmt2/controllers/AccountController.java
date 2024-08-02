package com.personnelmgmt2.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@GetMapping
	public ResponseEntity<Object> getAllAccounts() {
		return sendRestTemplateExchange(null, baseUrl, HttpMethod.GET);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Object> getAccountById(@PathVariable String id) {
		return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.GET);
	}
}
