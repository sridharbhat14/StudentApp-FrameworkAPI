package com.student.requests;

import java.util.Map;

import com.student.specs.SpecificationFactory;
import com.student.tests.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class RestClient extends TestBase{
	
	public Response doGetRequest(String requestPath) {
		
		return RestAssured.given()
                .when()
                .get(requestPath);
		
		
	}
	
	public Response doPostRequest(String uri,Object body) {
		return RestAssured.given()
				.contentType(ContentType.JSON)
				.spec(SpecificationFactory.logPayloadResponseInfo())
				.when()
				.body(body)
				.post(uri);
	}
	
	public Response doGetRequestWithQueryParam(String res, Map<String , String> params) {
		Response response = given()
				.queryParams(params)
				.when()
				.get(res);
		return response;
	}
	
	public Response doGetRequestWithHeader(String res , Map<String , String> headers) {
		Response response = given()
				.headers(headers)
				.when()
				.get(res);
		return response;
	}
	
	public Response doPutRequest(String res , Object body) {
		Response response = given()
				.when()
				.body(body)
				.put(res);
		return response;
	}

	public Response doPatchRequest(String res , Object body) {
		Response response = given()
				.when()
				.body(body)
				.patch(res);
		return response;
	}
	
	public Response doDeleteRequest(String res) {
		Response response = given()
				.when()
				.delete(res);
		return response;
	}
	

}
