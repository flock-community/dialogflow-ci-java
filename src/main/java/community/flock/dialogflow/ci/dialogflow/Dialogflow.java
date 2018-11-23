package community.flock.dialogflow.ci.dialogflow;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.DataFormatException;

import community.flock.dialogflow.ci.dialogflow.helper.RequestHelper;
import community.flock.dialogflow.ci.dialogflow.helper.ResponseHelper;
import community.flock.dialogflow.ci.dialogflow.helper.ResponseParser;
import community.flock.dialogflow.ci.dialogflow.helper.ZipUtils;
import community.flock.dialogflow.ci.json.BasicCard;
import community.flock.dialogflow.ci.json.DetectIntentResponseBody;
import community.flock.dialogflow.ci.json.Message;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Dialogflow {
	
	private RequestHelper requestHelper;
	private ResponseHelper responseHelper;
	private ResponseParser responseParser;
	
	private String lastResponse;
	private DetectIntentResponseBody body;

	public Dialogflow(String projectId, String token) {
		this.requestHelper = new RequestHelper(projectId, token);
		this.responseHelper = new ResponseHelper();
		this.responseParser = new ResponseParser();
	}

	public void say(String sentence) throws JsonProcessingException {
		this.lastResponse = requestHelper.query(sentence);
		body = null;
		System.out.println(lastResponse);
	}

	public String getIntent() throws JsonParseException, JsonMappingException, IOException {
		generateIntentBody();
		
		return responseHelper.getIntent(body);
	}
	
	public String getSpeech() throws JsonParseException, JsonMappingException, IOException {
		generateIntentBody();
		
		return responseHelper.getSpeech(body);
	}

	public BasicCard getBasicCard() throws JsonParseException, JsonMappingException, IOException {
		generateIntentBody();
		
		Optional<BasicCard> opt = responseHelper.getMessages(body).stream()
				.map(Message::getBasicCard)
				.filter(Objects::nonNull)
				.findFirst();
		
		if (opt.isPresent())
			return opt.get();
		else
			return null;
	}
	
	public void download(String dir) throws DataFormatException, IOException {
		String response = requestHelper.download();
		byte[] compressedData = responseHelper.getZipDataFrom(
				responseParser.parseOperationResponse(response));
		
		ZipUtils.unZip(dir, compressedData);
	}
	
	public void upload(String dir) throws JsonProcessingException {
		byte[] zipData = ZipUtils.zip(dir);
		requestHelper.upload(zipData);
	}

	
	private void generateIntentBody() throws JsonParseException, JsonMappingException, IOException {
		// TODO: raise a nice exception and end test as errored
		if (lastResponse == null)
			Assert.fail("No response from Dialogflow. Did you say something before checking the response?");
		
		// Only parse the body when we haven't already
		if (body == null)
			body = responseParser.parseDetectIntentResponse(lastResponse);
	}
}
