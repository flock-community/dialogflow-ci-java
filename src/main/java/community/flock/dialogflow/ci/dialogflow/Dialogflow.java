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
import community.flock.dialogflow.ci.dialogflow.helper.Device;
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
	private Device device;
	private String userId;

	public Dialogflow(String projectId, String token) {
		this.requestHelper = new RequestHelper(projectId, token);
		this.responseHelper = new ResponseHelper();
		this.responseParser = new ResponseParser();
		this.coveredIntents = new HashSet<>();
	}
	
	public void setDevice(Device device) {
		this.device = device;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Performs a query to the application corresponding to when a user provides a written/spoken sentence. 
	 * @param sentence The input to the application
	 * @throws JsonProcessingException When the response couldn't be parsed
	 */
	public void say(String sentence) throws JsonProcessingException {
		this.lastResponse = requestHelper.query(sentence, device, userId);
		body = null;
		System.out.println(lastResponse);
	}

	/**
	 * @return The detected intent name from the last received response
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String getIntent() throws JsonParseException, JsonMappingException, IOException {
		generateIntentBody();
		
		return responseHelper.getIntent(body);
	}
	
	/**
	 * @return The spoken response from the assistant in the last received response.
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String getSpeech() throws JsonParseException, JsonMappingException, IOException {
		generateIntentBody();
		
		return responseHelper.getSpeech(body);
	}

	/**
	 * @return The basic card from the last received response or null
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
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
	
	/**
	 * @return Coverage information on the application intents during this run
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws IOException
	 * @throws DataFormatException
	 */
	public CoverageInfo getCoverageInfo() throws JsonParseException, JsonMappingException, JsonProcessingException, IOException, DataFormatException {
		Set<String> intents = getAllIntentNames();
		
		return new CoverageInfo(intents, coveredIntents);
	}
	
	/**
	 * Download the intents/entities from the application to the given local directory
	 * @param dir
	 * @throws DataFormatException
	 * @throws IOException
	 */
	public void download(String dir) throws DataFormatException, IOException {
		ZipUtils.unZip(dir, getCompressedIntents());
	}
	
	/**
	 * Upload the intents/entities from the given local directory to the application
	 * @param dir
	 * @throws JsonProcessingException
	 */
	public void upload(String dir) throws JsonProcessingException {
		byte[] zipData = ZipUtils.zip(dir);
		requestHelper.upload(zipData);
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

	private byte[] getCompressedIntents()
			throws JsonProcessingException, DataFormatException, IOException, JsonParseException, JsonMappingException {
		String response = requestHelper.download();
		byte[] compressedData = responseHelper.getZipDataFrom(
				responseParser.parseOperationResponse(response));
		return compressedData;
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
