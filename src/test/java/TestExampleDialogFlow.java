import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "resources/features",
	glue= {"com.github.flock_se.dialogflow_ci.steps"},
	tags = {"~@Ignore"},
	plugin = {"pretty", "html:target/cucumber", "junit:target/cucumber.xml"},
	monochrome = true,
	dryRun = false
	)
public class TestExampleDialogFlow extends com.github.flock_se.dialogflow_ci.DialogflowTestRunner {
	
}
