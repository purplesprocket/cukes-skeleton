package StepDefinitions;

import cucumber.api.java.en.Given;
import utils.APIManager;
import utils.OutputType;

import java.util.Map;

public class RequestsSteps {

    @Given("^request for json output was sent with parameters:$")
    public void sendRequestWithParameters(Map<String, String> parameters){
        APIManager.getInstance().sendRequest(OutputType.JSON, parameters);

    }
}
