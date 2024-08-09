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
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

	@Value("${spring.datasource.username}") private String username;
	@Value("${spring.datasource.password}") private String password;
	@Value("${spring.datasource.url}/Accounts") private String baseUrl;
	
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
	 * Also contains a workaround for erroneous InternalServerErrors when successfully creating Accounts,
	 * returning 201 Created instead.
	 * @param e - The exception to process.
	 * @return A ResponseEntity containing details about the exception.
	 */
	private ResponseEntity<Object> processError(Exception e) {
		//	Workaround for Salesforce not sending success upon creating new account
		if(!e.toString().contains("detail")) {
			return ResponseEntity.status(201).header("Success", "Account successfully created")
					.body(null);
		}
		else {
			return ResponseEntity.status(500).header("Error", "SCIM Error " + e)
					.body("An error occurred when sending the request to SCIM:\n" + e);
		}
	}
	

	/**
	 * getAllAccounts()
	 * Returns all available Salesforce accounts, regardless of active status.
	 * @return All Accounts found in Salesforce. Includes inactive accounts.
	 */
	@GetMapping
	public ResponseEntity<Object> getAllAccounts() {
		try {
			return sendRestTemplateExchange(null, baseUrl, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * getAccountById()
	 * @param id - The id of the Account to find.
	 * @return The Account corresponding to the id parameter.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Object> getAccountById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * getAccountByIdentityId()
	 * A scrapped function that would ideally return all accounts corresponding to the id of a SailPoint identity.
	 * Currently does not function as expected, most likely an issue with RFC-7644 filtering.
	 * @param identityId - The id of the SailPoint identity to find accounts for.
	 * @return The Accounts corresponding to the SailPoint identity.
	 */
	@GetMapping("/byIdentityId/{identityId}")
	public ResponseEntity<Object> getAccountsByIdentityId(@PathVariable String identityId) {
		try {
			String filterById = "filter=identity.value eq \"" + identityId + "\"";
			return sendRestTemplateExchange(filterById, baseUrl + "/" + identityId, HttpMethod.GET);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * createAccount()
	 * Creates an account in Salesforce. Accounts created are unable to be updated through SCIM once created.
	 * Due to an error where SCIM returns 500 Internal Server Error with no details upon creating an Identity successfully,
	 * a workaround has been implemented to return 201 Created instead.
	 * Note: Only a certain amount of Salesforce accounts can be active at a time.
	 * Some may have to be "deleted" in order to create a new account.
	 * @param account - The details of the Account to create, sent as a JSON string to SCIM.
	 * @return 201 Created
	 */
	@PostMapping
	public ResponseEntity<Object> createAccount(@RequestBody Account account) {
		System.out.println(account.toJsonString());
		try {
			return sendRestTemplateExchange(account.toJsonString(), baseUrl, HttpMethod.POST);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * updateAccountById()
	 * A scrapped function, due to the fact that Salesforce identities cannot be updated through SCIM.
	 * Would ideally update a Salesforce identity in a similar manner to IdentityController.updateAccountByIdentity().
	 * Currently does not function as expected.
	 * @param id - The id of the Salesforce account to update.
	 * @param newFields - The new details to update the Salesforce account with.
	 * @return Details about the updated identity.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateAccountById(@PathVariable String id,
													@RequestBody Account newFields) {
		System.out.println(newFields.toJsonString());
		try {
			return sendRestTemplateExchange(newFields.toJsonString(), baseUrl + "/" + id, HttpMethod.PUT);
		} catch(Exception e) {return processError(e);}
	}
	
	/**
	 * deleteAccountById()
	 * Since Salesforce accounts cannot be truly deleted through SCIM,
	 * this call changes the "active" status of the account with ID id to false.
	 * @param id - THe id of the Salesforce account to "delete."
	 * @return 204 No Content.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteAccountById(@PathVariable String id) {
		try {
			return sendRestTemplateExchange(null, baseUrl + "/" + id, HttpMethod.DELETE);
		} catch(Exception e) {return processError(e);}
	}
}
