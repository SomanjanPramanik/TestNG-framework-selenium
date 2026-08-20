package com.creatio.crm.api.pages;

import org.json.JSONObject;

public class RepositoryApiPage {

	// request body for creating a repository
	public static String creatingRepository(String name, String description, boolean visibility) {
		JSONObject jo = new JSONObject();
		jo.put("name", name);
		jo.put("description", description);
		jo.put("private", visibility);
		return jo.toString();
	}

	// request body for updating a repository
	public static String updatingRepository(String name, String description, Boolean visibility) {
		JSONObject jo = new JSONObject();
		if (name != null) {
			jo.put("name", name);
		}
		if (description != null) {
			jo.put("description", description);
		}
		if (visibility != null) {
			jo.put("private", visibility);
		}
		return jo.toString();
	}

}
