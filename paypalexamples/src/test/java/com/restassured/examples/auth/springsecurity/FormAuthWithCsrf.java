package com.restassured.examples.auth.springsecurity;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;

public class FormAuthWithCsrf {
	public static SessionFilter filter;
	
	@BeforeClass
	public static void init() {
		
		filter = new SessionFilter();
		
		RestAssured.baseURI = "http://localhost:8090";
		RestAssured.given().auth().form("user", "user",new FormAuthConfig("/login","uname","pwd").withCsrfFieldName("_csrf"))
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
