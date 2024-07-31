package com.personnelmgmt2.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "spt_account_group")
public class Account {
	//	TODO Verify these data types.
	@Id
	@Column(name = "id") String id;
	@Column(name = "created") Date created;
	@Column(name = "modified") Date modified;
	@Column(name = "name") String name;
	@Column(name = "description") String description;
	@Column(name = "owner") Object owner;
	@Column(name = "application") Object application;
}
