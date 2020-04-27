package com.paypalexamples.base;
import org.junit.BeforeClass;
import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.*;


public class BaseClass {
	
	public static String accessToken;
	
	public static final String clientId = "AbO8xrIRWJn2bPsDucm7h6_Bm9x9cAvZcD1LghkyRMf1A1KJlttVeCx89Nblwh0MFiX2Wm73_ARAmg1h";
    public static final String clientSecret = "EJsuFdsyVYWYbHHXRwM50GRH_DEqO3iTbL3EcugIHLfG-d3uGfH8ICqNtIbeOFOawMk2FfXSOPFceTG8";
    
    @BeforeClass
    
    public static void getToken() {
    	
    	RestAssured.baseURI = "https://api.sandbox.paypal.com";
    	RestAssured.basePath = "/v1";
    	
    	 accessToken = given()
    	.parameters("grant_type","client_credentials")
    	.auth()
    	.preemptive()
    	.basic(clientId, clientSecret)
    	.when()
    	.post("/oauth2/token")
    	.then()
    	.log()
    	.all()
    	.extract()
    	 .path("access_token");
    	 System.out.println("The access token is: "+accessToken);
    	
    }
    
}
