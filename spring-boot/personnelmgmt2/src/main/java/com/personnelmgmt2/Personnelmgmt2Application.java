package com.personnelmgmt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//	Remove this exclusion at some point?
//	It disables the "Please sign in" page with unknown login credentials.
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Personnelmgmt2Application {

	public static void main(String[] args) {
		SpringApplication.run(Personnelmgmt2Application.class, args);
	}

}
