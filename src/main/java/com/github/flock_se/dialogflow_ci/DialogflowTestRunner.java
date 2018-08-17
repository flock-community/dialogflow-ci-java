package com.github.flock_se.dialogflow_ci;

import org.junit.runner.RunWith;

import com.github.flock_se.dialogflow_ci.dialogflow.Dialogflow;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "resources/cucumber",
	glue= {"com.github.flock_se.dialogflow_ci/steps"},
	//tags = {"@tag1"},
	plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml"},
	monochrome = true,
	dryRun = false
	)
public class DialogflowTestRunner {
	
	private Dialogflow app;

	public Dialogflow getApplication() {
		return app;
	}

	public void setApplication(Dialogflow app) {
		this.app = app;
	}
}
