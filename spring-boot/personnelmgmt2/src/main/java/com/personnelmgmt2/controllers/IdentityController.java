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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.personnelmgmt2.models.Identity;

@RestController
@RequestMapping("/identity") 
@CrossOrigin(origins = "*") // TODO Cross-Origin security
public class IdentityController {
	
	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${spring.datasource.url}/Users") private String baseUrl;
	
	private HttpHeaders generateAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
		return headers;
	}
	
	private ResponseEntity<Object> sendRestTemplateExchange(Object body, String url, HttpMethod method) {
		RestTemplate temp = new RestTemplate();
		HttpEntity<Object> entity = new HttpEntity<>(body, generateAuthHeaders());
		return temp.exchange(url, method, entity, Object.class);
	}
	
	private ResponseEntity<Object> processError(Exception e, String fromMethod) {
		//	TODO There is probably a way to formally extract error code from exception message
		int errorCode = Integer.parseInt(e.getMessage().substring(0, 3));
		System.out.println("\n\nEXCEPTION IN " + fromMethod + ": " + errorCode);
		return ResponseEntity.status(HttpStatus.valueOf(errorCode)).header("Error", "SCIM Error " + errorCode)
				.body("An error occurred when sending the request to SCIM:\n" + e);
	}
	
	
	@GetMapping("/test")
	public ResponseEntity<String> testConnection() {
		return ResponseEntity.status(418).body("Connection successful.");
	}

	@GetMapping
	public ResponseEntity<Object> getAllIdentities() {
		try {
			return sendRestTemplateExchange(null, baseUrl, HttpMethod.GET);
		} catch(Exception e) {return processError(e, "getAllIdentities()");}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getIdentityById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.GET);
		} catch(Exception e) {return processError(e, "getIdentityById()");}
	}
	
	@PostMapping
	public ResponseEntity<Object> createIdentity(@RequestBody Identity identity) {
		try {
			return sendRestTemplateExchange(identity, baseUrl, HttpMethod.POST);
		} catch(Exception e) {return processError(e, "createIdentity()");}
	}
	
	//	FIXME Same issue as POST
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateIdentityById(@PathVariable String id,
													@RequestBody Identity newFields) {
		try {
			return sendRestTemplateExchange(newFields, baseUrl + "/" + id, HttpMethod.PUT);
		} catch(Exception e) {return processError(e, "updateIdentityById");}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteIdentityById(@PathVariable String id) {
		//	SCIM DELETE does not return anything
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.DELETE);
		} catch(Exception e) {return processError(e, "deleteIdentityById");}
	}
}
