package com.personnelmgmt2.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spt_identity")
public class Identity {
	//	TODO Test these columns (and table name) with the remote SailPoint database
	@Id
	//	TODO Do I need @GeneratedValue here?
	@Column(name = "id") String id;
	@Column(name = "name") String username;
	@Column(name = "firstname") String firstname;
	@Column(name = "lastname") String lastname;
	@Column(name = "display_name") String displayName;
	@Column(name = "email") String email;
	@Column(name = "manager") String manager_id;
	@Column(name = "type") String type;
	//	TODO I don't know if I can do this, but I can try
	@Column(name = "assigned_role_summary") String[] assignedRoles;
	@Column(name = "password") Object password;	//	TODO Is there an 'encrypted string' value?
}
