package community.flock.dialogflow.ci.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import community.flock.dialogflow.ci.DialogflowTestRunner;
import community.flock.dialogflow.ci.dialogflow.Dialogflow;
import community.flock.dialogflow.ci.json.BasicCard;
import cucumber.api.DataTable;
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

		Dan("^begrijpt (?:ze|de assistente) dat ik \"([^\"]*)\" bedoel$", (String intent) -> {
			testIntent(intent);
		});

		Dan("^begrijpt (?:ze|de assistente) dat ik de \"([^\"]*)\" intentie heb$", (String intent) -> {
			testIntent(intent);
		});

		Dan("^(?:ze|de assistente) (?:zegt|vraagt) \"([^\"]*)\"$", (String speech) -> {
			testSpeech(speech);
		});
		
		Dan("^een eenvoudige kaart wordt getoond met:$", (DataTable cardTable) -> {
			Map<String, String> values = cardTable.asMap(String.class, String.class);
			BasicCard basicCard = null;
			try {
				basicCard = context.getApplication().getBasicCard();
			} catch (IOException e) {
				fail("Failed to parse response: " + e.getMessage());
			}

			assertEqualsOrNull(values, "title", basicCard.getTitle());
			assertEqualsOrNull(values, "subtitle", basicCard.getSubtitle());
			assertEqualsOrNull(values, "formattedText", basicCard.getFormattedText());
		});
	}

	private void assertEqualsOrNull(Map<String, String> values, String key, String actual) {
		if (values.containsKey(key))
			assertEquals(String.format("unexpected %s", key), values.get(key), actual);
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
