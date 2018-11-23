package community.flock.dialogflow.ci.dialogflow.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import community.flock.dialogflow.ci.json.DetectIntentResponseBody;
import community.flock.dialogflow.ci.json.Operation;

public class ResponseParser {
	
	public DetectIntentResponseBody parseDetectIntentResponse(String response)
			throws IOException, JsonParseException, JsonMappingException {
		
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
		DetectIntentResponseBody body = om.readValue(response, DetectIntentResponseBody.class);
		
		return body;
	}
	
	public Operation parseOperationResponse(String response)
			throws IOException, JsonParseException, JsonMappingException {
		
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
		Operation body = om.readValue(response, Operation.class);
		
		return body;
	}
}
