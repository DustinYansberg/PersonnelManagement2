package com.personnelmgmt2.models;

import org.springframework.beans.factory.annotation.Value;

public class Account {
	@Value("${spring.datasource.url}") private static String baseUrl;

	//	Variables for required details for accounts connected to SailPoint.
	//	All variables below are required unless stated otherwise.
	String accountAppId;
//	String accountUserDisplayName;	//	Not required. Is NOT the property 'displayName' of resource 'User'.
	String accountUserId;
	String nativeIdentity;
	String accountDisplayName;
	String instanceId;				//	Not required, but cannot change once set.
	String password;				//	Required?
	String currentPassword;			//	Required?
	boolean active;					//	Must be set true in POST.
	boolean locked;
	boolean manuallyCorrelated;
	boolean hasEntitlements;
	
	//	Name of connected app as specified in SCIM schema.
	//	Because some accounts are for Salesforce and some are for Azure Entra ID.
	String accountAppName;			//	Required. Must either be "Salesforce" or "Azure Entra ID"
	
	//	Salesforce app variables. All of these are required for creating Salesforce apps.
	String salesforceUsername;		//	MUST BE IN EMAIL FORM
	String salesforceLastName;
	String salesforceFirstName;
	String salesforceCommunityNickname;
	String salesforceAlias;			//	Alphanumeric, must be 8 characters long and unique.
	String salesforceEmail;
	//	Constant values to not change.
	static final String timeZoneSidKey = "America/Los_Angeles";
	static final String localeSidKey = "en_US";
	static final String emailEncodingKey = "UTF-8";
	static final String languageLocaleKey = "en_US";

	//	Constructor function for Account.
	public Account(String accountAppId, String accountUserDisplayName, String accountUserId, String nativeIdentity,
			String accountDisplayName, String instanceId, String password, String currentPassword, boolean active,
			boolean locked, boolean manuallyCorrelated, boolean hasEntitlements, String accountAppName,
			String salesforceUsername, String salesforceLastName, String salesforceFirstName,
			String salesforceCommunityNickname, String salesforceAlias, String salesforceEmail) {
		super();
		this.accountAppId = accountAppId;
//		this.accountUserDisplayName = accountUserDisplayName;
		this.accountUserId = accountUserId;
		this.nativeIdentity = nativeIdentity;
		this.accountDisplayName = accountDisplayName;
		this.instanceId = instanceId;
		this.password = password;
		this.currentPassword = currentPassword;
		this.active = active;
		this.locked = locked;
		this.manuallyCorrelated = manuallyCorrelated;
		this.hasEntitlements = hasEntitlements;
		this.accountAppName = accountAppName;
		this.salesforceUsername = salesforceUsername;
		this.salesforceLastName = salesforceLastName;
		this.salesforceFirstName = salesforceFirstName;
		this.salesforceCommunityNickname = salesforceCommunityNickname;
		this.salesforceAlias = salesforceAlias;
		this.salesforceEmail = salesforceEmail;
	}

	/**
	 * toJsonString()
	 * Converts this Account object into a string that can be passed as a valid request body
	 * for a request to SCIM API.
	 * @return JSON string containing Account details, formatted for SCIM requests
	 */
	public String toJsonString() {
		String asJson = "{\r\n"
			+ "  \"identity\": {\r\n"
//			+ "    \"displayName\": \"" + accountUserDisplayName + "\",\r\n"
			+ "    \"value\": \"" + accountUserId + "\"\r\n"
			+ "  },\r\n"
			+ "  \"application\": {\r\n"
			+ "    \"value\": \"" + accountAppId + "\"\r\n"
			+ "  },\r\n";
		if(nativeIdentity != null) {
			asJson = asJson + "  \"nativeIdentity\": \"" + nativeIdentity + "\",\r\n";
		}
		asJson = asJson
			+ "  \"displayName\": \"" + accountDisplayName + "\",\r\n"
//			+ "  \"instance\": \"" + instanceId + "\",\r\n"
//			+ "  \"password\": \"" + password + "\",\r\n"
//			+ "  \"currentPassword\": \"" + currentPassword + "\",\r\n"
			+ "  \"urn:ietf:params:scim:schemas:sailpoint:1.0:Application:Schema:" + accountAppName + ":account\": {\r\n";
		if(accountAppName.equals("Salesforce")) {
			asJson = asJson
				+ "    \"ProfileName\": \"Standard User\",\r\n"
				+ "    \"Username\": \"" + salesforceUsername + "\",\r\n"
				+ "    \"LastName\": \"" + salesforceLastName + "\",\r\n"
				+ "    \"FirstName\": \"" + salesforceFirstName + "\",\r\n"
				+ "    \"CommunityNickname\": \"" + salesforceCommunityNickname + "\",\r\n"
				+ "    \"Alias\": \"" + salesforceAlias + "\",\r\n"
				+ "    \"Email\": \"" + salesforceEmail + "\",\r\n"
				+ "    \"TimeZoneSidKey\": \"" + timeZoneSidKey + "\",\r\n"
				+ "    \"LocaleSidKey\": \"" + localeSidKey + "\",\r\n"
				+ "    \"EmailEncodingKey\": \"" + emailEncodingKey + "\",\r\n"
				+ "    \"LanguageLocaleKey\": \"" + languageLocaleKey + "\"\r\n";
//				+ "    \"User\": {\"displayName\": \"TESTDISPLAYNAME\"}\r\n";
		}
		asJson = asJson + "  },\r\n"
			+ "  \"active\": " + active + ",\r\n"
			+ "  \"locked\": " + locked + "\r\n"
			+ "}";
		return asJson;
		
		
	}
	
}
