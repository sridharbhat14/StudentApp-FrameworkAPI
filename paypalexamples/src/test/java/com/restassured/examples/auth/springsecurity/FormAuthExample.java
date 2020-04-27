package com.restassured.examples.auth.springsecurity;

import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;

import static com.jayway.restassured.RestAssured.*;

public class FormAuthExample {

	 public static SessionFilter filter;
	
	@BeforeClass
	public static void init() {
		
		filter = new SessionFilter();
		
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.given().auth().form( "user", "user", new FormAuthConfig("/login","uname","pwd"))
		.filter(filter)
		.get();
		
		System.err.println(filter.getSessionId());
		
	}
	
	@Test
	public void getAllStudents() {
		RestAssured.given()
		.sessionId(filter.getSessionId())
		.get("/Student/list")
		.then()
		.log()
		.all();
		
	}

}
