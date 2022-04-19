package com.Inderjit.com.BackendlessRestAPI;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class UserLogin extends UserRegistration {
	
	JSONObject jsonObject;
	
	// Go to BaseURI
	@Before
	public void beforeTest() {
		// Using randomEmail generator from BackendLoginApi
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}

	@Test
	public void verifyUserLogin() {
		request = RestAssured.given();

		request.header("Content-Type", "application/json");

		jsonObject.put("email", respEmail);
		jsonObject.put("password", "password");
		
		request.body(jsonObject);
		response = request.post("/login");
		System.out.println(response.statusCode());

	}

}
