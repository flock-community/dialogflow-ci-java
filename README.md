# dialogflow-ci-java
Cucumber steps for testing DialogFlow apps. Java edition

## Creating test scenarios
Test scenarios are written using Cucumber: https://cucumber.io/

An example test scenario is given in features/example.feature

## Setup authentication
Follow the steps here to setup authentication for your project:
  https://dialogflow.com/docs/reference/v2-auth-setup
This will let you generate access tokens.

## Running the Example project
Clone/Download this from Github. From the root of this directory run:
```
 mvn test -DprojectID=test-13cdd -Dtoken=$(GOOGLE_APPLICATION_CREDENTIALS=<TODO: generate key file which is only allowed to test> gcloud auth application-default print-access-token)
```

This tests the feature in resources/features/example.feature against an example Dialogflow application.

## Install/Run
Add the dialogflow-ci-java package to your project. Create a test runner such as the src/test/java/TestExampleDialogFlow.java runner from this project's Github.
  
Then you can run your features from the command line with:
```
mvn test -DProjectID=<your project id> -Dtoken=<your access token>
```

## Adding test steps
In order to add cucumber steps, create a class in your project such as:
```
import com.github.flock_se.dialogflow_ci.DialogflowTestRunner;
import cucumber.api.java8.Nl;

public class MySteps extends DialogflowTestRunner implements Nl {	
	public MySteps(DialogflowTestRunner context) {
		super(context);
		
		Dan("<your step>", () -> {
			...
		});
	}
}
```

If you are not Dutch and/or not want to run steps in Dutch, you can copy all steps from DialogflowTestRunner and translate them. Instead of implementing Nl, implement your language from cucumber.api.java8. Then you can run feature files in your language.

## Dowloading/Uploading projects

TODO: command

This downloads/uploads all the intents/entities from your project as json files to/from the specified directory.
