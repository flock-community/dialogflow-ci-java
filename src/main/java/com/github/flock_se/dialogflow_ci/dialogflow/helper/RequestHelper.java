package com.github.flock_se.dialogflow_ci.dialogflow.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.flock_se.dialogflow_ci.json.Context;
import com.github.flock_se.dialogflow_ci.json.DetectIntentRequestBody;
import com.github.flock_se.dialogflow_ci.json.QueryInput;
import com.github.flock_se.dialogflow_ci.json.QueryParameters;
import com.github.flock_se.dialogflow_ci.json.TextInput;

public class RequestHelper {
	
	private static Logger log = Logger.getAnonymousLogger();

	private static final String BASE_URL = "https://dialogflow.googleapis.com/v2";
	private String token;
	private String projectId;
	private UUID sessionId;

	public RequestHelper( String projectId, String token) {
		this.token = token;
		this.projectId = projectId;		
		this.sessionId = UUID.randomUUID();
	}

	public String query(String sentence) throws JsonProcessingException {
		log.info("HIER");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL).path(
				String.format("/projects/%s/agent/sessions/%s:detectIntent", projectId, sessionId));
		
		String json = generateQueryRequestBody(sentence);
		
		System.out.println(target.getUri().toString());
		System.out.println(json);
		try {
			String bean =
				target.request(MediaType.APPLICATION_JSON_TYPE)
					  .header("Authorization", "Bearer " + token)
					  .header("Content-Type", "application/json")
				      .post(Entity.entity(json, MediaType.APPLICATION_JSON_TYPE), 
				    		  	String.class);

			
			return bean;
		} catch(javax.ws.rs.WebApplicationException e) {
			System.out.println(e.getResponse().readEntity(String.class));
			throw e;
		}
	}

	private String generateQueryRequestBody(String sentence) throws JsonProcessingException {
		DetectIntentRequestBody body = new DetectIntentRequestBody();
		QueryParameters queryParameters = new QueryParameters();
		body.setQueryParams(queryParameters);
		queryParameters.setTimeZone("Netherlands/Amsterdam");
		List<Context> contexts = new ArrayList<Context>();
		queryParameters.setContexts(contexts);
		QueryInput queryInput = new QueryInput();
		body.setQueryInput(queryInput);
		TextInput textInput = new TextInput();
		queryInput.setText(textInput);
		textInput.setText(sentence);
		textInput.setLanguageCode("nl");
		
		return convertToJson(body);
	}

	private String convertToJson(Object body) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
		ObjectWriter ow = om.writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(body);
		return json;
	}
}
