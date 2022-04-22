package com.Inderjit.com.BackendlessRestAPI;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SecondApproach {
	// Random email geneerator to register new email
	String randomEmail = RandomStringUtils.randomNumeric(3);
	String email;

	RequestSpecification request;
	JSONObject jsonObject;
	Response response;
	JsonPath path;
	String respEmail;

	String objectId;
	String ownerId;

	// Go to BaseURI
	@Before
	public void beforeTest() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
	}

	@Test()
	public void testUserRegistration() {
		// Create a request object
		request = RestAssured.given();

		// Add header
		request.header("Content-Type", "application/json");

		// Add request body
		jsonObject = new JSONObject();
		email = "is" + randomEmail + "@gmail.com";
		jsonObject.put("email", email);
		jsonObject.put("password", "password");

		request.body(jsonObject);

		response = request.post("/register");

		Assert.assertEquals(200, response.statusCode());

		System.out.println("Status code :" + response.statusCode());

		path = response.jsonPath();
		respEmail = path.getString("email");
		System.out.println("respEmail : " + respEmail);

		// Getting the JSON Object
		System.out.println(path.get());
		// Parsing and storing value from JSON objectId and ownerId
		objectId = path.getString("objectId");
		ownerId = path.getString("ownerId");
		System.out.println("Object ID : " + objectId);
		System.out.println("OwnerId: " + ownerId);

	}

	@Test
	public void testLogin() {

		// Adding Request body for User Login Api, email is the response from previous
		// API
		request = RestAssured.given();
		request.header("Content-Type", "application/json");

		jsonObject = new JSONObject();

		jsonObject.put("email", respEmail);
		jsonObject.put("password", "password");

		request.body(jsonObject);
		response = request.post("/login");
		System.out.println(response.statusCode());
		Assert.assertEquals(200, response.statusCode());
		path = response.jsonPath();

		// path.get() : prints the response code after send
		System.out.println(path.get());

	}

	@Test
	public void testUpdate() {
		// Add header user update
		request.header("Content-Type", "application/json");
		request.header("user-token", ownerId);

		jsonObject = new JSONObject();

		// REQUEST BODY
		jsonObject.put("email", respEmail);
		jsonObject.put("password", "password");

		// Send REQUEST
		response = request.put(objectId);
		System.out.println(response.statusCode());
		Assert.assertEquals(200, response.statusCode());
		path = response.jsonPath();

		System.out.println(path.get());
	}
}
