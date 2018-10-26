package community.flock.dialogflow.ci.dialogflow;

import java.io.IOException;
import java.util.zip.DataFormatException;

import community.flock.dialogflow.ci.dialogflow.helper.RequestHelper;
import community.flock.dialogflow.ci.dialogflow.helper.ResponseHelper;
import community.flock.dialogflow.ci.dialogflow.helper.ZipUtils;
import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class Dialogflow {
	
	private String lastResponse;
	private RequestHelper requestHelper;
	private ResponseHelper responseHelper;

	public Dialogflow(String projectId, String token) {
		this.requestHelper = new RequestHelper(projectId, token);
		this.responseHelper = new ResponseHelper();
	}

	public void say(String sentence) throws JsonProcessingException {
		this.lastResponse = requestHelper.query(sentence);
		System.out.println(lastResponse);
	}

	public String getIntent() throws JsonParseException, JsonMappingException, IOException {
		// TODO: raise a nice exception
		if (lastResponse == null)
			Assert.fail("No response! (getIntent)");
		
		return responseHelper.getIntent(lastResponse);
	}
	
	public String getSpeech() throws JsonParseException, JsonMappingException, IOException {
		// TODO: raise a nice exception
		if (lastResponse == null)
			Assert.fail("No response! (getIntent)");
		
		return responseHelper.getSpeech(lastResponse);
	}
	
	public void download(String dir) throws DataFormatException, IOException {
		String response = requestHelper.download();
		byte[] compressedData = responseHelper.getZipDataFrom(response);
		
		ZipUtils.unZip(dir, compressedData);
	}
	
	public void upload(String dir) throws JsonProcessingException {
		byte[] zipData = ZipUtils.zip(dir);
		requestHelper.upload(zipData);
	}
}
