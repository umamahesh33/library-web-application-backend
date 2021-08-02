package com.minorproject.libraryapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO: get all books assigned to the student :DONE
//TODO:welcome page after login and home page with non authenticated :DONE
//TODO:ADD AUTHENTICATION TO BOOK CONTROLLERS :DONE
//TODO:AUTHENTICATE TRANSACTIONS :DONE
//TODO:ADD ADMIN ROLE AND ADD ADMIN SPECIFIC REST ENDPOINTS :DONE
//TODO:ADD REDIS CACHE
//TODO:

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {

		SpringApplication.run(LibraryApplication.class, args);
	}

}
