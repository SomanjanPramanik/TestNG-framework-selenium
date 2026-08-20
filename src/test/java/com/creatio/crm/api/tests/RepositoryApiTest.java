package com.creatio.crm.api.tests;

import org.testng.annotations.Test;

import com.creatio.crm.api.pages.RepositoryApiPage;
import com.creatio.crm.framework.api.commons.ApiCommons;
import com.creatio.crm.framework.utilities.PropUtils;

public class RepositoryApiTest extends ApiCommons {

	public String user = PropUtils.propReadData("config.properties").getProperty("user");
	public String name = PropUtils.propReadData("config.properties").getProperty("repository");
	public String description = PropUtils.propReadData("config.properties").getProperty("description");
	public boolean visibility = Boolean
			.parseBoolean(PropUtils.propReadData("config.properties").getProperty("private"));
	public String newName = "NewSampleRepositryFromPostMan";
	public String newDescription = "This is your updated repository";
	
	// 1. verify repository doesn't exist before creating
	@Test(priority = 1)
	public void verifyNoRepositoryBeforeCreatingForAuthenticatedUser() {
		getAuthHeaders();
		getResponse("GET", "/repos/" + user + "/" + name, "");
		verifyStatusCode(404);
		verifyStatusMessage("Not Found");
		verifyResponseTime(3000);
		verifyResponseBody("message", "Not Found");
		verifyResponseBody("status", "404");
	}

	// 2. verify creating repository for authenticated user
	@Test(priority = 2)
	public void verifyForCreatingRepositoryForAuthenticatedUser() {
		getAuthHeaders();
		getHeaders("Accept", "application/vnd.github+json");
		String requestBody = RepositoryApiPage.creatingRepository(name, description, visibility);
		getResponse("POST", "/user/repos",requestBody);
		verifyStatusCode(201);
		verifyStatusMessage("Created");
		verifyResponseTime(3000);
		verifyResponseBody("name", name);
		verifyResponseBody("description", description);
		verifyResponseBody("full_name", user + "/" + name);
		verifyResponseBody("owner.login", user);
		verifyResponseBody("private", String.valueOf(visibility));
	}

	// 3. verify duplicate creation fails
	@Test(priority = 3)
	public void verifyForCreatingDuplicateRepositoryForAuthenticatedUser() {
		getAuthHeaders();
		getHeaders("Accept", "application/vnd.github+json");
		String requestBody = RepositoryApiPage.creatingRepository(name, description, visibility);
		getResponse("POST", "/user/repos",requestBody);
		verifyStatusCode(422);
		verifyStatusMessage("Unprocessable Entity");
		verifyResponseTime(3000);
		verifyResponseBody("message", "Repository creation failed.");
		verifyResponseBody("errors[0].message", "name already exists on this account");
	}

	// 4. verify creating repository for unauthenticated user
	@Test(priority = 4)
	public void verifyForCreatingRepositoryForUnauthenticatedUser() {
		// no getAuthHeaders() call - deliberately unauthenticated
		//getAuthHeaders();
		getHeaders("Accept", "application/vnd.github+json");
		String requestBody = RepositoryApiPage.creatingRepository(name, description, visibility);
		getResponse("POST", "/user/repos",requestBody);
		verifyStatusCode(401);
		verifyStatusMessage("Unauthorized");
		verifyResponseTime(3000);
		verifyResponseBody("message", "Requires authentication");
	}

	// 5. verify getting the repository which now exists
	@Test(priority = 5)
	public void verifyGettingRepositoryWhichExistsForAuthenticatedUser() {
		getAuthHeaders();
		getResponse("GET", "/repos/" + user + "/" + name, "");
		verifyStatusCode(200);
		verifyStatusMessage("OK");
		verifyResponseTime(3000);
		verifyResponseBody("name", name);
		verifyResponseBody("description", description);
		verifyResponseBody("full_name", user + "/" + name);
		verifyResponseBody("owner.login", user);
		verifyResponseBody("private", String.valueOf(visibility));
	}

	// 6. verify updating visibility to public
	@Test(priority = 6)
	public void verifyUpdatingVisibilityToPublic() {
		getAuthHeaders();
		String requestBody = RepositoryApiPage.updatingRepository(null, null, false);
		getResponse("PATCH", "/repos/" + user + "/" + name,requestBody);
		verifyStatusCode(200);
		verifyStatusMessage("OK");
		verifyResponseTime(3000);
		verifyResponseBody("private", "false");
	}

	// 7. verify updating name and description
	@Test(priority = 7)
	public void verifyUpdatingNameAndDescription() {
		getAuthHeaders();
		String requestBody = RepositoryApiPage.updatingRepository(newName, newDescription, null);
		getResponse("PATCH", "/repos/" + user + "/" + name,requestBody);
		verifyStatusCode(200);
		verifyStatusMessage("OK");
		verifyResponseTime(3000);
		verifyResponseBody("name", newName);
		verifyResponseBody("description", newDescription);
		
	}

	// 8. verify getting the renamed repository
	@Test(priority = 8)
	public void verifyGettingRenamedRepository() {
		getAuthHeaders();
		getResponse("GET", "/repos/" + user + "/" + newName, "");
		verifyStatusCode(200);
		verifyStatusMessage("OK");
		verifyResponseTime(3000);
		verifyResponseBody("name", newName);
		verifyResponseBody("description", newDescription);
		verifyResponseBody("full_name", user + "/" + newName);
		verifyResponseBody("owner.login", user);
		verifyResponseBody("private", String.valueOf(visibility));
	}

	// 9. verify deleting the repository
	@Test(priority = 9)
	public void verifyDeletingRepositoryForAuthenticatedUser() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		getAuthHeaders();
		getResponse("DELETE", "/repos/" + user + "/" + newName, "");
		verifyStatusCode(204);
		verifyStatusMessage("No Content");
		verifyResponseTime(3000);
	}
}