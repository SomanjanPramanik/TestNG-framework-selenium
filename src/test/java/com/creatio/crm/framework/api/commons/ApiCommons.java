package com.creatio.crm.framework.api.commons;

import com.creatio.crm.framework.reports.Reports;
import com.creatio.crm.framework.utilities.PropUtils;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

public class ApiCommons extends Reports{

	public static Response response = null;
	public static Map<String, String> headers = null;

	// Get response
	public static void getResponse(String requestType, String endpoints, String requestBody) {

		RestAssured.baseURI = PropUtils.propReadData("config.properties").getProperty("Base_url");

		switch (requestType) {
		case ("DELETE"):
			response = given().headers(headers).delete(endpoints);
			break;
		case ("GET"):
			response = given().headers(headers).get(endpoints);
			break;
		case ("POST"):
			response = given().headers(headers).body(requestBody).post(endpoints);
			break;
		case ("PUT"):
			response = given().headers(headers).body(requestBody).put(endpoints);
			break;
		case ("PATCH"):
			response = given().headers(headers).body(requestBody).patch(endpoints);
			break;
		default:
			Assert.fail("Invalid request type :" + requestType);
			Reports.printInReport("fail", "Invalid request type :" + requestType);
			break;
		}

		// auto-reset right after the request fires
		headers = new HashMap<>();
	}

	// Add Authentication headers to your request body
	public static void getAuthHeaders() {

		if (headers == null) {
			headers = new HashMap<>();
		}
		headers.put("Authorization", PropUtils.propReadData("config.properties").getProperty("Bearer_token"));

	}

	// Add other headers to main headers
	// headers.put("Accept", "application/vnd.github+json");
	public static void getHeaders(String key, String value) {

		if (headers == null) {
			headers = new HashMap<>();
		}
		headers.put(key, value);

	}

	// verify status code
	public static void verifyStatusCode(int expStatusCode) {

		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, expStatusCode);
		Reports.printInReport("pass", "Status code matched , actual status code is : " + actualStatusCode);

	}

	// verify status message
	public static void verifyStatusMessage(String expStatusMessage) {

		String actualStatusMessage = response.getStatusLine();
		Assert.assertTrue(actualStatusMessage.contains(expStatusMessage));
		Reports.printInReport("pass", "Status message matched , actual status message is : " + actualStatusMessage);

	}

	// verify response time
	public static void verifyResponseTime(long expResponseTimeInMiliSeconds) {

		long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		Assert.assertTrue(actualResponseTime <= expResponseTimeInMiliSeconds);
		Reports.printInReport("pass", "Response time , actual response time is : " + actualResponseTime);

	}

	// verify response body
	public static void verifyResponseBody(String key, String expValue) {

		String actualValue = response.getBody().jsonPath().getString(key);
		Assert.assertEquals(actualValue, expValue);
		Reports.printInReport("pass",
				"Response body matched , actual value is : " + actualValue + " for the key : " + key);

	}

	// verify key from response body (e.g. we want to verify if key is not null)
	public static void verifyKeyFromResponseBody(String key) {

		String actualKey = response.getBody().jsonPath().get(key);
		Assert.assertNotNull(actualKey);
		Reports.printInReport("pass", "Response body is expected , the key is present : " + actualKey);

	}

	// verify the headers
	public static void verifyHeaders(String key, String expValue) {

		String actualValue = response.getHeader(key);
		Assert.assertEquals(actualValue, expValue);
		Reports.printInReport("pass", "Header is matched , actual value is : " + actualValue + " for the key : " + key);

	}
}
