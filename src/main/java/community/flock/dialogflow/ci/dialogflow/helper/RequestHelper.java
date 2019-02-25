package community.flock.dialogflow.ci.dialogflow.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import community.flock.dialogflow.ci.json.Capability;
import community.flock.dialogflow.ci.json.Context;
import community.flock.dialogflow.ci.json.DetectIntentRequestBody;
import community.flock.dialogflow.ci.json.ImportRequestBody;
import community.flock.dialogflow.ci.json.Payload;
import community.flock.dialogflow.ci.json.QueryInput;
import community.flock.dialogflow.ci.json.QueryParameters;
import community.flock.dialogflow.ci.json.Surface;
import community.flock.dialogflow.ci.json.TextInput;
import community.flock.dialogflow.ci.json.User;

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

	public String query(String sentence, Device device, String userId) throws JsonProcessingException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL).path(
				String.format("/projects/%s/agent/sessions/%s:detectIntent", projectId, sessionId));
		
		String json = generateQueryRequestBody(sentence, device, userId);
		System.out.println(target.getUri().toString());
		System.out.println(json);
		
		return doRequest(target, json);
	}
	
	public String download() throws JsonProcessingException {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL).path(
				String.format("/projects/%s/agent:export", projectId));
		
		String json = "{}";
		
		return doRequest(target, json);
	}

	public void upload(byte[] zipData) throws JsonProcessingException {
		String payload = DatatypeConverter.printBase64Binary((zipData));
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(BASE_URL).path(
				String.format("/projects/%s/agent:import", projectId));
		
		String json = generateImportRequestBody(payload);
		
		doRequest(target, json);
	}
	

	private String doRequest(WebTarget target, String json) {
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

	private String generateQueryRequestBody(String sentence, Device device, String userId) throws JsonProcessingException {
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
		
		Payload payload = new Payload();
		Surface surface = generateSurface(device);
		payload.setSurface(surface);
		
		User user = generateUser(userId);
		payload.setUser(user);

		queryParameters.setPayload(payload);
		
		return convertToJson(body);
	}

	private User generateUser(String userId) {
		User user = new User();
		user.setUserId(userId);
		user.setLocale("nl-NL");
		return user;
	}

	private Surface generateSurface(Device device) {
		Surface surface = new Surface();
		List<Capability> capabilities = new ArrayList<>();
		surface.setCapabilities(capabilities);
		Capability audioOutput = new Capability();
		audioOutput.setName("actions.capability.AUDIO_OUTPUT");
		Capability screenOutput = new Capability();
		screenOutput.setName("actions.capability.SCREEN_OUTPUT");
		Capability webBrowser = new Capability();
		webBrowser.setName("actions.capability.WEB_BROWSER");
		Capability mediaResponseAudio = new Capability();
		mediaResponseAudio.setName("actions.capability.MEDIA_RESPONSE_AUDIO");
		
		switch(device) {
		case NONE: 
			return null;
		case PHONE:
			capabilities.add(webBrowser);
		case SMART_DISPLAY:
			capabilities.add(screenOutput);
		case SPEAKER:
			capabilities.add(audioOutput);
			capabilities.add(mediaResponseAudio);
		}
		return surface;
	}
	
	private String generateImportRequestBody(String payload) throws JsonProcessingException {
		ImportRequestBody body = new ImportRequestBody();
		body.setAgentContent(payload);
		
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
