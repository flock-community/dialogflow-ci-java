package com.github.flock_se.dialogflow_ci.dialogflow;

import com.github.flock_se.dialogflow_ci.dialogflow.helper.RequestHelper;

public class Dialogflow {
	
	private String application;
	private String lastResponse;
	private RequestHelper requestHelper;

	public Dialogflow(String application, String token, String projectId) {
		this.application = application;
		this.requestHelper = new RequestHelper(token, projectId);
		
		// TODO: create session id
	}

	public void say(String sentence) {
		this.lastResponse = requestHelper.query(sentence);
	}

}
