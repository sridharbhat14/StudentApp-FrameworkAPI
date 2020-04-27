package com.restassured.examples.jsoup;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;

public class JsoupExamples {
	 public static SessionFilter filter;
	
	@BeforeClass
	public static void init() {
		
		filter = new SessionFilter();
		
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.given().auth().form("user", "user",new FormAuthConfig("/login","uname","pwd"))
		.filter(filter)
		.get();
		
		System.err.println(filter.getSessionId());
		
	}

	
	@Test
	public void extractTitles() {
		String response=  RestAssured.given().when().get("http://localhost:8080/").asString();
		
		Document document = Jsoup.parse(response);
		System.out.println("The title of the page is:" +document.title().toUpperCase());
	}
	
	@Test
	public void extractingElementsByTag() {
		String response=  RestAssured.given().when().get("http://localhost:8080/").asString();
		
		Document document = Jsoup.parse(response);
		
		Elements element = document.getElementsByTag("form");
		System.out.println("The number of form Elements is:  "+ element.size());
		
		for(Element e:element) {
			System.out.println(e);
			
		}
		
		
}
	@Test
	public void extractingElementsByID() {
		String response=  RestAssured.given().when().get("http://localhost:8080/").asString();
		
		Document document = Jsoup.parse(response);
		
		Element element = document.getElementById("command");
		System.out.println("The element contents are:  "+ element.text());
		
		
		
}
	
	@Test
	public void extractingLinks() {
		String response=  RestAssured.given().when().get("http://localhost:8080/").asString();
		
		Document document = Jsoup.parse(response);
		
		Elements links = document.select("a[href]");
		
		
		for(Element e:links) {
			//System.out.println(e.text());
			
		}
	}
	
	@Test
	public void extractingEmailInformation() {
		String response=  RestAssured.given().sessionId(filter.getSessionId()).when().get("/student/list").asString();
		
		Document document = Jsoup.parse(response);
		
		Elements emailId = document.select("table tbody tr td:nth-child(4)");
		
	System.out.println("The size of the table is: " +emailId.size());
	ArrayList<String> actualValue = new ArrayList<String>();
	for(Element e:emailId) {
		//System.out.println(e.text());
		
		actualValue.add(e.text());
		
	}
	
	assertThat(actualValue,hasItem("Quisque.tincidunt@nullaanteiaculis.co.uk"));
	}
}
