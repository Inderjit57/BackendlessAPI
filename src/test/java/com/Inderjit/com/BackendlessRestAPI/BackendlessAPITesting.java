package com.Inderjit.com.BackendlessRestAPI;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BackendlessAPITesting {
	RequestSpecification request;
	JSONObject jsonObject;
	Response response;
	JsonPath path;

	String objectId = "A0AAAC06-D753-4532-B014-E6366A5ACFE9";
	String userToken = "CED34BC4-97A1-45EC-ABA6-3A483811FE25";

	// Go to BaseURI
	@Before
	public void beforeTest() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";
		request = RestAssured.given();
	}

	@Test
	public void testLogin() {

		request.header("Content-Type", "application/json");

		// Header
		jsonObject = new JSONObject();
		jsonObject.put("login", "is726@gmail.com");
		jsonObject.put("password", "password");

		// Response
		request.body(jsonObject);
		response = request.post("/login");
		System.out.println("Response source code = " + response.statusCode());
		System.out.println(response.asPrettyString());
		Assert.assertEquals(200, response.statusCode());
		path = response.jsonPath();

		// Parsing and storing value from JSON objectId and ownerId
		userToken = path.getString("user-token");
		objectId = path.getString("ownerId");
		System.out.println("User Token : " + userToken);
		System.out.println("Object Id: " + objectId);

	}

	@Test
	public void testUpdate() {
		// Add header user update
		request.header("Content-Type", "application/json");
		request.header("user-token", "CED34BC4-97A1-45EC-ABA6-3A483811FE25");

		// REQUEST BODY
		jsonObject = new JSONObject();
		jsonObject.put("login", "is726@gmail.com");
		jsonObject.put("password", "password");

		// Send REQUEST
		response = request.put("/" + objectId);
		System.out.println("status code = " + response.statusCode());
		System.out.println(response.asPrettyString());
		Assert.assertEquals(200, response.statusCode());
		path = response.jsonPath();

	}
}
