package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features",
        glue = "StepDefinitions",
        tags = { "@Automated" },
        plugin = {"pretty", "html:target/html-cucumber", "junit:target/junit-cucumber", "json:target/json-cucumber"}
)
public class RunCukesTest {
}