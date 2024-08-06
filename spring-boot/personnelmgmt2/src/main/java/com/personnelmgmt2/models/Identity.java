package com.personnelmgmt2.models;

public class Identity {
	String id;
	//	Values we can normally fill in from the SailPoint Create Identity form
	String userName;
	Name name; //	name : {formatted, familyName, givenName}
	String displayName;
	String userType;
//	boolean active;	//	If the user is an administrator or not. May not need this.
	String password;
	//	emails: [{type, value, primary}, ...]
	Email[] emails;
	//	Is "urn:ietf:params:scim:schemas:sailpoint:1.0:User" in the SCIM request body
	User urn_ietf_params_scim_schemas_sailpoint_1_0_User;
	//	Is "urn:ietf:params:scim:schemas:extension:enterprise:2.0:User"
	User2 urn_ietf_params_scim_schemas_extension_enterprise_2_0_User;
	
	public Identity() {}
	
	//	Constructor used by frontend requests.
	public Identity(String identityName, String password, String firstName, String lastName,
					String email, String manager, String softwareVersion, String administrator,
					String displayName, /*boolean isActive,*/ String type, String department) {
		this.name = new Name(identityName, firstName, lastName);
		this.password = password;
		this.emails = new Email[1];
		emails[0] = new Email("default", email, true);
		
	}
	
	//	Name object transfer class.
	class Name {
		String formatted;
		String familyName;	//	last name.
		String givenName;	//	first name.
		
		public Name(String formatted, String familyName, String givenName) {
			this.formatted = formatted;
			this.familyName = familyName;
			this.givenName = givenName;
		}
	}
	
	//	Email object transfer class.
	class Email {
		String type;
		String value;
		boolean primary;
		
		public Email(String type, String value, boolean primary) {
			this.type = type;
			this.value = value;
			this.primary = primary;
		}
	}
	
	//	1.0 User object transfer class.
	class User {
//		UserAccount[] accounts;
//		UserEntitlement[] entitlements;
//		UserRole[] roles;
//		String capabilities;
//		int riskScore;
//		boolean isManager;
		UserAccount administrator;
		String softwareVersion;
//		String empId;
//		String dn;
//		String region;
//		UserAccount regionOwner;
//		String location;
//		UserAccount locationOwner;
		String Department;
//		String[] costcenter;
		String jobtitle;
//		LocalDateTime lastRefresh;
		
		class UserEntitlement {
			
		}
		
		class UserRole {
			
		}
	}
	
	class UserAccount {
		String displayName;
		String value;
		String $ref;
		
		public UserAccount(String displayName) {
			this.displayName = displayName;
		}
	}
	
	//	2.0 User transfer class.
	class User2 {
		UserAccount manager;
	}
}
