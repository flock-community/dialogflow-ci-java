package community.flock.dialogflow.ci.dialogflow.helper;

import java.io.IOException;
import java.util.zip.DataFormatException;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import community.flock.dialogflow.ci.json.DetectIntentResponseBody;
import community.flock.dialogflow.ci.json.Operation;

public class ResponseHelper {

	public String getIntent(String lastResponse) throws JsonParseException, JsonMappingException, IOException {
		DetectIntentResponseBody body = parseDetectIntentResponse(lastResponse);
		
		return body.getQueryResult().getIntent().getDisplayName();
	}
	
	public String getSpeech(String lastResponse) throws JsonParseException, JsonMappingException, IOException {
		DetectIntentResponseBody body = parseDetectIntentResponse(lastResponse);
		
		return body.getQueryResult().getFulfillmentText();
	}

	public byte[] getZipDataFrom(String response) throws DataFormatException, IOException {
		Operation operation = parseOperationResponse(response);
		String zipData = operation.getResponse().getAgentContent();
		
		return DatatypeConverter.parseBase64Binary(zipData);
	}
	

	private DetectIntentResponseBody parseDetectIntentResponse(String lastResponse)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
		DetectIntentResponseBody body = om.readValue(lastResponse, DetectIntentResponseBody.class);
		
		return body;
	}
	
	private Operation parseOperationResponse(String lastResponse)
			throws IOException, JsonParseException, JsonMappingException {
		ObjectMapper om = new ObjectMapper();
		om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		om.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);
		Operation body = om.readValue(lastResponse, Operation.class);
		
		return body;
	}
}
