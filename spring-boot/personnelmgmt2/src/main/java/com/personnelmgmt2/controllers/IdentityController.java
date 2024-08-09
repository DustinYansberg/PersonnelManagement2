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

import com.personnelmgmt2.models.Identity;

@RestController
@RequestMapping("/identity") 
@CrossOrigin(origins = "localhost:4200")
public class IdentityController {
	
	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${spring.datasource.url}/Users") private String baseUrl;
	
	/**
	 * generateAuthHeaders()
	 * A function for generating required SailPoint authorization headers for operations.
	 * @return An HttpHeaders object with encoded Basic Auth headers.
	 */
	private HttpHeaders generateAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes()));
		return headers;
	}
	
	/**
	 * sendRestTemplateExchange()
	 * A function to pass various types of RestTemplate exchanges without having to retype code.
	 * @param body - The object to send. For GET and DELETE, is null.
	 * @param url - The url to send to.
	 * @param method - The CRUD operation to perform, as an HttpMethod.
	 * @return A ResponseEntity containing the results of the exchange.
	 */
	private ResponseEntity<Object> sendRestTemplateExchange(Object body, String url, HttpMethod method) {
		RestTemplate temp = new RestTemplate();
		HttpEntity<Object> entity = new HttpEntity<>(body, generateAuthHeaders());
		return temp.exchange(url, method, entity, Object.class);
	}
	
	/**
	 * processError()
	 * A function to process and display SCIM-related errors with further details.
	 * @param e - The exception to process.
	 * @return A ResponseEntity containing details about the exception.
	 */
	private ResponseEntity<Object> processError(Exception e) {
		//	TODO There is probably a way to formally extract error code from exception message
		return ResponseEntity.status(500).header("Error", "SCIM Error " + e)
				.body("An error occurred when sending the request to SCIM:\n" + e);
	}
	
	/**
	 * testConnection()
	 * A testing function to verify connection to remote app deployment.
	 * @return HttpStatus 418
	 */
	@GetMapping("/test")
	public ResponseEntity<String> testConnection() {
		return ResponseEntity.status(418).body("Connection successful.");
	}

	/**
	 * getAllIdentities()
	 * @return All Identities (Users) found in SailPoint.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllIdentities() {
		try {
			return sendRestTemplateExchange(null, baseUrl, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * getIdentityById()
	 * @param id - The id of the Identity to find.
	 * @return The Identity corresponding to the id parameter.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getIdentityById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * createIdentity()
	 * @param identity - Details of the Identity to create, sent as a JSON string to SCIM.
	 * @return Details about the created identity.
	 */
	@PostMapping
	public ResponseEntity<Object> createIdentity(@RequestBody Identity identity) {
		try {
			return sendRestTemplateExchange(identity.toJsonString(), baseUrl, HttpMethod.POST);
		} catch(Exception e) {return processError(e);}
	}

	/**
	 * updateIdentityById()
	 * Updates the Identity specified by id with new information.
	 * Note: SCIM will auto-fill omitted values with null when updating.
	 * @param id - The id of the Identity to update.
	 * @param newFields
	 * @return Information about the updated identity.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateIdentityById(@PathVariable String id,
													@RequestBody Identity newFields) {
		try {
			return sendRestTemplateExchange(newFields.toJsonString(), baseUrl + "/" + id, HttpMethod.PUT);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 deleteIdentityById()
	 * @param id - The id of the Identity to delete.
	 * @return 204 No Content.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteIdentityById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.DELETE);
		} catch(Exception e) {return processError(e);}
	}
}
