package com.student.tests;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import com.student.util.PropertyReader;

import io.restassured.RestAssured;

public class TestBase {
	
	public static PropertyReader prop;
	
	@Rule
	public TestRule listener = new TestWatcher() {
		

		protected void Succeeded(Description description) {
			
			System.out.println("*********************************************");
			System.out.println(description.getMethodName().toUpperCase());
			System.out.println("*********************************************");
			
		}
	
	};
	
	
	@BeforeClass
	public static void initUrl() {
		
		prop = PropertyReader.getInstance();
		
		RestAssured.baseURI = prop.getProperty("baseUrl");
		RestAssured.port = Integer.valueOf(prop.getProperty("port"));
		
	}

}
