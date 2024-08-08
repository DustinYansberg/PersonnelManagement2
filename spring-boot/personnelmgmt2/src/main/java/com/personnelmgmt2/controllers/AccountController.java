package com.personnelmgmt2.controllers;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

import com.personnelmgmt2.models.Account;

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*") // TODO Cross-Origin security
public class AccountController {

	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${spring.datasource.url}/Accounts") private String baseUrl;
	
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
		return ResponseEntity.status(500).header("Error", "SCIM Error " + e)
				.body("An error occurred when sending the request to SCIM:\n" + e);
	}
	
	
	@GetMapping
	public ResponseEntity<Object> getAllAccounts() {
		try {
			return sendRestTemplateExchange(null, baseUrl, HttpMethod.GET);
		} catch(Exception e) {return processError(e, "getAllAccounts()");}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAccountById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.GET);
		} catch(Exception e) {return processError(e, "getAccountById()");}
	}
	
	@GetMapping("/byIdentityId/{identityId}")
	public ResponseEntity<Object> getAccountsByIdentityId(@PathVariable String identityId) {
		try {
			String filterById = "filter=identity.value eq \"" + identityId + "\"";
			return sendRestTemplateExchange(filterById, baseUrl + "/" + identityId, HttpMethod.GET);
		} catch(Exception e) {return processError(e, "getAccountsByIdentityId");}
	}
	
	//	FIXME Error message "Please provide all required fields." while all fields provided
	@PostMapping
	public ResponseEntity<Object> createAccount(@RequestBody Account account) {
		System.out.println(account.toJsonString());
		try {
			return sendRestTemplateExchange(account.toJsonString(), baseUrl, HttpMethod.POST);
		} catch(Exception e) {return processError(e, "createAccount()");}
	}
	
	//	TODO Test remote
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAccountById(@PathVariable String id,
													@RequestBody Account newFields) {
		try {
			return sendRestTemplateExchange(newFields.toJsonString(), baseUrl + "/" + id, HttpMethod.PUT);
		} catch(Exception e) {return processError(e, "updateAccountById");}
	}
	
	//	TODO Test remote
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAccountById(@PathVariable String id) {
		//	SCIM DELETE does not return anything
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.DELETE);
		} catch(Exception e) {return processError(e, "deleteAccountById");}
	}
}
