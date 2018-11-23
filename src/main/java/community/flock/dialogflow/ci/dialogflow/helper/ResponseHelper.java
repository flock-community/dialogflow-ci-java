package community.flock.dialogflow.ci.dialogflow.helper;

import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;

import javax.xml.bind.DatatypeConverter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import community.flock.dialogflow.ci.json.DetectIntentResponseBody;
import community.flock.dialogflow.ci.json.Message;
import community.flock.dialogflow.ci.json.Operation;
import community.flock.dialogflow.ci.json.SimpleResponse;

public class ResponseHelper {

	public String getIntent(DetectIntentResponseBody body) throws JsonParseException, JsonMappingException, IOException {
		return body.getQueryResult().getIntent().getDisplayName();
	}
	
	public String getSpeech(DetectIntentResponseBody body) throws JsonParseException, JsonMappingException, IOException {
		String text = body.getQueryResult().getFulfillmentText();
		List<Message> messages = body.getQueryResult().getFulfillmentMessages();
		int i = 0;
		while(text == null && i < messages.size()) {
			SimpleResponse[] responses = messages.get(i).getSimpleResponses().getSimpleResponses();
			if (responses != null) {
				int j = 0;
				while(text == null && j < responses.length) {
					text = responses[j].getTextToSpeech();
					j++;
				}
			}
			i++;
		}
		return text;
	}

	public List<Message> getMessages(DetectIntentResponseBody body) throws JsonParseException, JsonMappingException, IOException {
		return body.getQueryResult().getFulfillmentMessages();
	}

	public byte[] getZipDataFrom(Operation operation) throws DataFormatException, IOException {
		String zipData = operation.getResponse().getAgentContent();
		
		return DatatypeConverter.parseBase64Binary(zipData);
	}
}
