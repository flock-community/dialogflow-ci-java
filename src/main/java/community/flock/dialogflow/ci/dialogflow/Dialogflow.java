package community.flock.dialogflow.ci.dialogflow;

import static java.util.stream.Collectors.toSet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.zip.DataFormatException;
import java.util.zip.ZipEntry;

import community.flock.dialogflow.ci.dialogflow.helper.CoverageInfo;
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
	private Set<String> coveredIntents;

	public Dialogflow(String projectId, String token) {
		this.requestHelper = new RequestHelper(projectId, token);
		this.responseHelper = new ResponseHelper();
		this.responseParser = new ResponseParser();
		this.coveredIntents = new HashSet<>();
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
	
	public CoverageInfo getCoverageInfo() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, DataFormatException {
		Set<String> intents = getAllIntentNames();
		
		return new CoverageInfo(intents, coveredIntents);
	}

	private Set<String> getAllIntentNames()
			throws IOException, JsonProcessingException, DataFormatException, JsonParseException, JsonMappingException {
		return ZipUtils.getZipEntries(getCompressedIntents()).stream()
				.map(ZipEntry::getName)
				.filter(name -> name.startsWith("intents/"))
				.map(name -> name.replace("intents/", ""))
				.map(name -> name.replace(".json", ""))
				.collect(toSet());
	}
	
	public void download(String dir) throws DataFormatException, IOException {
		ZipUtils.unZip(dir, getCompressedIntents());
	}

	private byte[] getCompressedIntents()
			throws JsonProcessingException, DataFormatException, IOException, JsonParseException, JsonMappingException {
		String response = requestHelper.download();
		byte[] compressedData = responseHelper.getZipDataFrom(
				responseParser.parseOperationResponse(response));
		return compressedData;
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
		if (body == null) {
			body = responseParser.parseDetectIntentResponse(lastResponse);
			coveredIntents.add(responseHelper.getIntent(body));
		}
	}
}
