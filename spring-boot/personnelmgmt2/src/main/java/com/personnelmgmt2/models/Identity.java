package com.personnelmgmt2.models;

import java.util.Arrays;

public class Identity {
	String id;
	
	//	Values we can normally fill in from the SailPoint Create Identity form
	String userName;
	String password;
	String firstname;
	String lastname;
	String email;
	String manager_id;
	String administrator_id;
	String displayName;
	String type;
	String department;
	String[] assignedRoles;
	
	public Identity() {}
	
	public Identity(String username, String password, String firstname, String lastname, String email,
			String manager_id, String administrator_id, String displayName, String type, String department,
			String[] assignedRoles) {
		this.userName = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.manager_id = manager_id;
		this.administrator_id = administrator_id;
		this.displayName = displayName;
		this.type = type;
		this.department = department;
		this.assignedRoles = assignedRoles;
	}
	
	public Identity(String id, String username, String password, String firstname, String lastname, String email,
			String manager_id, String administrator_id, String displayName, String type, String department,
			String[] assignedRoles) {
		this.id = id;
		this.userName = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.manager_id = manager_id;
		this.administrator_id = administrator_id;
		this.displayName = displayName;
		this.type = type;
		this.department = department;
		this.assignedRoles = assignedRoles;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getManager_id() {
		return manager_id;
	}

	public void setManager_id(String manager_id) {
		this.manager_id = manager_id;
	}

	public String getAdministrator_id() {
		return administrator_id;
	}

	public void setAdministrator_id(String administrator_id) {
		this.administrator_id = administrator_id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String[] getAssignedRoles() {
		return assignedRoles;
	}

	public void setAssignedRoles(String[] assignedRoles) {
		this.assignedRoles = assignedRoles;
	}

	@Override
	public String toString() {
		return "Identity [id=" + id + ", username=" + userName + ", password=" + password + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", email=" + email + ", manager_id=" + manager_id + ", administrator_id="
				+ administrator_id + ", displayName=" + displayName + ", type=" + type + ", department=" + department
				+ ", assignedRoles=" + Arrays.toString(assignedRoles) + "]";
	}
	
}
