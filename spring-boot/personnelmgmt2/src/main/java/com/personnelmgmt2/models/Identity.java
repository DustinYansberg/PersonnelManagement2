package com.personnelmgmt2.models;

import org.springframework.beans.factory.annotation.Value;

public class Identity {
	@Value("${spring.datasource.url}/Users") private static String baseUrl;
	
	String userName;		//	Required for POST and PUT, but cannot be changed by PUT.
	String password;
	String firstName;
	String lastName;
	String email;
	String managerId;
	String softwareVersion;
	String administratorId;
	String displayName;
	boolean active;			//	If the user is an administrator or not. May not need this.
	String userType;		//	Required for POST and PUT.
	String department;
	
	public Identity(String userName, String password, String firstName, String lastName, String email,
			/*String manager,*/ String managerId, String softwareVersion, /*String administrator,*/ String administratorId,
			String displayName, boolean active, String userType, String department) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.managerId = managerId;
		this.softwareVersion = softwareVersion;
		this.administratorId = administratorId;
		this.displayName = displayName;
		this.active = active;
		this.userType = userType;
		this.department = department; 
	}
	
	/**
	 * This method converts this object into a format that SCIM likes.
	 * @return A JSON string that matches this object's variables.
	 */
	public String toJsonString() {
		String asJson = "{\r\n"
				+ "  \"userName\": \"" + userName + "\",\r\n"
				+ "  \"name\": {\r\n"
				+ "    \"formatted\": \"" + firstName + " " + lastName + "\",\r\n"
				+ "    \"familyName\": \"" + lastName + "\",\r\n"
				+ "    \"givenName\": \"" + firstName + "\"\r\n"
				+ "  },\r\n"
				+ "  \"displayName\": \"" + displayName + "\",\r\n"
				+ "  \"userType\": \"" + userType + "\",\r\n"
				+ "  \"active\": " + active + ",\r\n"
				+ "  \"password\": " + password + ",\r\n"
				+ "  \"emails\": [\r\n"
				+ "    {\r\n"
				+ "      \"type\": \"default\",\r\n"
				+ "      \"value\": \"" + email + "\",\r\n"
				+ "      \"primary\": \"true\"\r\n"
				+ "    }\r\n"
				+ "  ],\r\n"
				+ "  \"urn:ietf:params:scim:schemas:sailpoint:1.0:User\": {\r\n";

		if(/*administrator != null && */administratorId != null)
			asJson = asJson
				+ "    \"administrator\": {\r\n"
//				+ "      \"displayName\": \"" + administrator + "\",\r\n"
				+ "      \"value\": \"" + administratorId + "\",\r\n"
				+ "      \"$ref\": \"" + baseUrl + "/" + administratorId + "\"\r\n" 
				+ "    },\r\n";
		asJson = asJson
				+ "    \"softwareVersion\": \"" + softwareVersion + "\",\r\n"
				+ "    \"Department\": \"" + department + "\"\r\n"
				+ "  },\r\n";
		if(/*manager != null && */managerId != null)
			asJson = asJson
				+ "  \"urn:ietf:params:scim:schemas:extension:enterprise:2.0:User\": {\r\n"
				+ "    \"manager\": {\r\n"
//				+ "      \"displayName\": \"" + manager + "\",\r\n"
				+ "      \"value\": \"" + managerId + "\",\r\n"
				+ "      \"$ref\": \"" + baseUrl + "/" + managerId + "\"\r\n" 
				+ "    }\r\n"
				+ "  },\r\n";
		asJson = asJson
				+ "  \"schemas\": [\r\n"
				+ "    \"urn:ietf:params:scim:schemas:sailpoint:1.0:User\",\r\n"
				+ "    \"urn:ietf:params:scim:schemas:core:2.0:User\",\r\n"
				+ "    \"urn:ietf:params:scim:schemas:extension:enterprise:2.0:User\"\r\n"
				+ "  ]\r\n"
				+ "}";
		return asJson;
	}
}
