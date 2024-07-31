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
@RequestMapping("/identity") 
@CrossOrigin(origins = "*") // TODO Cross-Origin security
public class IdentityController {
//	@Autowired private IdentityService service;
	
	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${spring.datasource.url}") private String baseUrl;
	
	private ResponseEntity<Object> sendRestTemplateExchange(String url, HttpMethod method) {
		RestTemplate temp = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		return temp.exchange(url, method, entity, Object.class);
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> testConnection() {
		return ResponseEntity.status(418).body("Connection successful.");
	}

	@GetMapping
	public ResponseEntity<Object> getAllIdentities() {
		return sendRestTemplateExchange(baseUrl + "/Users", HttpMethod.GET);
	}
	
//	@GetMapping("/{id}")
//	public ResponseEntity<Object> getIdentityById(@PathVariable String id) {
//		return sendRestTemplateExchange(baseUrl + "/Users", HttpMethod.GET)
//	}
}
