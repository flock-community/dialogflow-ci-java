package com.github.flock_se.dialogflow_ci.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.flock_se.dialogflow_ci.DialogflowTestRunner;
import com.github.flock_se.dialogflow_ci.dialogflow.Dialogflow;

import cucumber.api.java8.Nl;

public class DialogflowSteps implements Nl {
	protected DialogflowTestRunner context;
	
	public DialogflowSteps(DialogflowTestRunner context) {
		this.context = context;
		
		Stel("^ik begin een gesprek met de \"([^\"]*)\" Google Home applicatie$", (String application) -> {
		    String projectId = System.getProperty("projectID");
		    String token = System.getProperty("token");
			Dialogflow app = new Dialogflow(projectId, token);
			context.setApplication(app);
		});

		Als("^ik zeg \"([^\"]*)\"$", (String sentence) -> {
		    try {
				context.getApplication().say(sentence);
			} catch (JsonProcessingException e) {
				fail("Failed to generate the request: " + e.getMessage());
			}
		});

		Dan("^begrijpt de assistente dat ik \"([^\"]*)\" bedoel$", (String intent) -> {
			testIntent(intent);
		});

		Dan("^ze vraagt \"([^\"]*)\"$", (String arg1) -> {
		});

		Dan("^begrijpt ze dat ik de \"([^\"]*)\" intentie heb$", (String intent) -> {
			testIntent(intent);
		});

		Dan("^de assistente zegt \"([^\"]*)\"$", (String speech) -> {
			testSpeech(speech);
		});
	}

	private void testSpeech(String speech) {
		try {
			assertEquals(speech, context.getApplication().getSpeech());
		} catch (IOException e) {
			fail("Failed to parse response: " + e.getMessage());
		}
	}

	private void testIntent(String intent) {
		try {
			assertEquals(intent, context.getApplication().getIntent());
		} catch (IOException e) {
			fail("Failed to parse response: " + e.getMessage());
		}
	}
}
