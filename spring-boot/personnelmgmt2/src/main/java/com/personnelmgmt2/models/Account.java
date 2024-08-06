package com.personnelmgmt2.models;

import org.springframework.beans.factory.annotation.Value;

public class Account {
	@Value("${spring.datasource.url}") private static String baseUrl;

	String accountAppId;	//	Required.
	String accountUserId;	//	Required.
	String nativeIdentity;	//	Required.
	String displayName;
	String instanceId;		//	Cannot change once set.
	String password;		//	Required?
	String currentPassword;	//	Required?
	boolean active;			//	Must be set true in POST.
	boolean locked;
	boolean manuallyCorrelated;
	boolean hasEntitlements;
	
	//	Because some accounts are for Salesforce and some are for Azure Entra ID.
	String accountAppName;	//	Required. Is either "Salesforce" or "Azure Entra ID"
	String appSpecificProperties;	//	"property": "value", "property": "value", ...

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
		return "{\r\n"
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
				+ "  \"urn:ietf:params:scim:schemas:sailpoint:1.0:Application:Schema:" + accountAppName + ":account\": {" + appSpecificProperties + "},\r\n"
				+ "  \"active\": " + active + ",\r\n"
				+ "  \"locked\": " + locked + "\r\n"
				+ "}";
	}
}
