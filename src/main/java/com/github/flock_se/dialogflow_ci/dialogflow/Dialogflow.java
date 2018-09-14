package com.github.flock_se.dialogflow_ci.dialogflow;

import java.io.IOException;

import org.junit.Assert;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.github.flock_se.dialogflow_ci.dialogflow.helper.RequestHelper;
import com.github.flock_se.dialogflow_ci.dialogflow.helper.ResponseHelper;

public class Dialogflow {
	
	private String application;
	private String lastResponse;
	private RequestHelper requestHelper;
	private ResponseHelper responseHelper;

	public Dialogflow(String application, String projectId, String token) {
		this.application = application;
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
}
