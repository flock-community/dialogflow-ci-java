package com.github.flock_se.dialogflow_ci.steps;

import com.github.flock_se.dialogflow_ci.DialogflowTestRunner;
import com.github.flock_se.dialogflow_ci.dialogflow.Dialogflow;

import cucumber.api.PendingException;
import cucumber.api.java8.Nl;

public class DialogflowSteps implements Nl {
	private DialogflowTestRunner context;
	
	public DialogflowSteps(DialogflowTestRunner context) {
		this.context = context;
		
		Stel("^ik begin een gesprek met de \"([^\"]*)\" Google Home applicatie$", (String application) -> {
		    String projectId = System.getProperty("projectID");
		    String token = System.getProperty("token");
			Dialogflow app = new Dialogflow(application, projectId, token);
			context.setApplication(app);
		});

		Als("^ik zeg \"([^\"]*)\"$", (String sentence) -> {
			System.out.println("HIER");
		    context.getApplication().say(sentence);
		});

		Dan("^begrijpt de assistente dat ik \"([^\"]*)\" bedoel$", (String arg1) -> {
		});

		Dan("^ze vraagt \"([^\"]*)\"$", (String arg1) -> {
		});

		Dan("^begrijpt ze dat ik de \"([^\"]*)\" intentie heb$", (String arg1) -> {
		});

		Dan("^de assistente zegt \"([^\"]*)\"$", (String arg1) -> {
		});
	}
}
