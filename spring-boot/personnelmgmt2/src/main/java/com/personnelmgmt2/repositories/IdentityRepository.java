package com.personnelmgmt2.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personnelmgmt2.models.Identity;

@Repository
public interface IdentityRepository extends CrudRepository<Identity, String>{
	
}
