package com.github.flock_se.dialogflow_ci.dialogflow.helper;

import java.util.UUID;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class RequestHelper {

	private static final String BASE_URL = "https://dialogflow.googleapis.com/v2";
	private String token;
	private String projectId;
	private UUID sessionId;

	public RequestHelper(String token, String projectId) {
		this.token = token;
		this.projectId = projectId;
		
		this.sessionId = UUID.randomUUID();
	}

	public String query(String sentence) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL).path(
				String.format("projects/%s/agent/sessions/%s", projectId, sessionId));
		 
		Form form = new Form();
		form.param("queryParams", "foo");
		form.param("queryInput", "bar");
		 
		String bean =
			target.request(MediaType.APPLICATION_JSON_TYPE)
			    .post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE),
			        String.class);
		System.out.println(bean);
		return bean;
	}
}
