package community.flock.dialogflow.ci.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Map;
import java.util.zip.DataFormatException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import community.flock.dialogflow.ci.DialogflowTestRunner;
import community.flock.dialogflow.ci.dialogflow.Dialogflow;
import community.flock.dialogflow.ci.dialogflow.helper.CoverageInfo;
import community.flock.dialogflow.ci.json.BasicCard;
import cucumber.api.DataTable;
import cucumber.api.java8.Nl;

public class DialogflowSteps implements Nl {
	protected DialogflowTestRunner context;
	
	private static boolean dunit = false;

	@cucumber.api.java.Before
    public void beforeAll() {
        if(!dunit) {
            Runtime.getRuntime().addShutdownHook(new PrintCoverageThread());
            dunit = true;
        }
    }
	
	class PrintCoverageThread extends Thread {
        public void run() {
        	printCoverage();
        }
    }
	
	public void printCoverage() {
		CoverageInfo coverageInfo;
		System.out.println("");
		try {
			coverageInfo = context.getApplication().getCoverageInfo();

			System.out.println(String.format("Covered intents (%d/%d):", 
					coverageInfo.getCoveredIntents().size(),
					coverageInfo.getAllIntents().size()));
			for(String intent : coverageInfo.getCoveredIntents())
				System.out.println(intent);
	
			System.out.println("");
			
			System.out.println(String.format("Uncovered intents  (%d/%d):", 
						coverageInfo.getUncoveredIntents().size(),
						coverageInfo.getAllIntents().size()));
			for(String intent : coverageInfo.getUncoveredIntents())
				System.out.println(intent);
		} catch (IOException | DataFormatException e) {
			System.out.println("Could not generate coverage info!");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
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
