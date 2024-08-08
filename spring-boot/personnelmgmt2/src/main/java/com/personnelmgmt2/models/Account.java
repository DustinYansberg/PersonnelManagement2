package com.personnelmgmt2.models;

import org.springframework.beans.factory.annotation.Value;

public class Account {
	@Value("${spring.datasource.url}") private static String baseUrl;

	String accountAppId;	//	Required.
	String accountUserId;	//	Required.
	String nativeIdentity;	//	Required in the body, even if it's blank
	String displayName;
	String instanceId;		//	Cannot change once set.
	String password;		//	Required?
	String currentPassword;	//	Required?
	boolean active;			//	Must be set true in POST.
	boolean locked;
	boolean manuallyCorrelated;
	boolean hasEntitlements;
	
	//	Because some accounts are for Salesforce and some are for Azure Entra ID.
	String accountAppName;	//	Required. Must either be "Salesforce" or "Azure Entra ID"
	//	All of these are required for Salesforce apps.
	String salesforceUsername;	//	MUST BE IN EMAIL FORM
	String salesforceLastName;
	String salesforceFirstName;
	String salesforceCommunityNickname;
	String salesforceAlias;	//	Alphanumeric, must be 8 characters long and unique.
	String salesforceEmail;
	static final String timeZoneSidKey = "America/Los_Angeles";
	static final String localeSidKey = "en_US";
	static final String emailEncodingKey = "UTF-8";
	static final String languageLocaleKey = "en_US";
	

	public Account(String accountAppId, String accountUserId, String nativeIdentity, String displayName,
			String instanceId, String password, String currentPassword, boolean active, boolean locked,
			boolean manuallyCorrelated, boolean hasEntitlements, String accountAppName, String appSpecificProperties) {
		super();
		this.accountAppId = accountAppId;
		this.accountUserId = accountUserId;
		this.nativeIdentity = nativeIdentity;
		this.displayName = displayName;
		this.instanceId = instanceId;
		this.password = password;
		this.currentPassword = currentPassword;
		this.active = active;
		this.locked = locked;
		this.manuallyCorrelated = manuallyCorrelated;
		this.hasEntitlements = hasEntitlements;
		this.accountAppName = accountAppName;
	}

	public String toJsonString() {
		String asJson = "{\r\n"
			+ "  \"identity\": {\r\n"
			+ "    \"value\": \"" + accountUserId + "\"\r\n"
			+ "  },\r\n"
			+ "  \"application\": {\r\n"
			+ "    \"value\": \"" + accountAppId + "\"\r\n"
			+ "  },\r\n"
			+ "  \"nativeIdentity\": \"" + nativeIdentity + "\",\r\n"
			+ "  \"displayName\": \"" + displayName + "\",\r\n"
			+ "  \"instance\": \"" + instanceId + "\",\r\n"
			+ "  \"password\": \"" + password + "\",\r\n"
			+ "  \"currentPassword\": \"" + currentPassword + "\",\r\n"
			+ "  \"urn:ietf:params:scim:schemas:sailpoint:1.0:Application:Schema:" + accountAppName + ":account\": {";
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
				+ "    \"LanguageLocaleKey\": \"" + languageLocaleKey + "\",\r\n";
		}
		asJson = asJson + "  },\r\n"
			+ "  \"active\": " + active + ",\r\n"
			+ "  \"locked\": " + locked + "\r\n"
			+ "}";
		return asJson;
	}
}
