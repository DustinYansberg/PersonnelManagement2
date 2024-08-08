package com.personnelmgmt2.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.personnelmgmt2.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String>{

}
