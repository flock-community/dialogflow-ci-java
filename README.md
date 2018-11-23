# dialogflow-ci-java
Cucumber steps for testing DialogFlow apps. Java edition

## Creating test scenarios
Test scenarios are written using Cucumber: https://cucumber.io/

An example test scenario is given in features/example.feature

## Setup authentication
Follow the steps here to setup authentication for your project:
  https://dialogflow.com/docs/reference/v2-auth-setup
This will let you generate access tokens.

## The Example project
In the resources/features directory there is an example feature for a simple Dialogflow project.

Go to https://travis-ci.org/flock-se/dialogflow-ci-example. The feature is tested against the example Dialogflow application here.

## Install/Run
Add the dialogflow-ci-java package to your project. Create a test runner such as the src/test/java/TestExampleDialogFlow.java runner from this project's Github.
  
Then you can run your features from the command line with:

```
mvn test -DprojectID=<your project id> -Dtoken=<your access token>
```

## Adding test steps
In order to add cucumber steps, create a class in your project such as:

```
import community.flock.dialogflow_ci.DialogflowTestRunner;
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

```
mvn exec:java -Dexec.mainClass="community.flock.dialogflow_ci.DialogflowCi" -Dexec.args="download -p=<projectId> -t=<token> -d=<target>"
```

This downloads all the intents/entities from your project as json files to the specified target directory. Replace download with upload to upload all intents/entities from the target directory to your project.
