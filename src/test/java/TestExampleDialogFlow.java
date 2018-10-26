import community.flock.dialogflow.ci.DialogflowTestRunner;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "resources/features",
	glue= {"community.flock.dialogflow.ci.steps"},
	tags = {"~@Ignore"},
	plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml"},
	monochrome = true,
	dryRun = false
	)
public class TestExampleDialogFlow extends DialogflowTestRunner {
	
}
