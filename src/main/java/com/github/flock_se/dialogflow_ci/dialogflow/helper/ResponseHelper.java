package com.github.flock_se.dialogflow_ci.dialogflow.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.github.flock_se.dialogflow_ci.json.DetectIntentResponseBody;

public class ResponseHelper {

	public String getIntent(String lastResponse) throws JsonParseException, JsonMappingException, IOException {
		DetectIntentResponseBody body = parseResponse(lastResponse);
		
		return body.getQueryResult().getIntent().getDisplayName();
	}

	private DetectIntentResponseBody parseResponse(String lastResponse)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
		DetectIntentResponseBody body = om.readValue(lastResponse, DetectIntentResponseBody.class);
		
		return body;
	}
}
