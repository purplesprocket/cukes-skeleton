package StepDefinitions;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import utils.APIManager;
import utils.AppSettings;
import validators.JSONValidator;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

public class ResponseSteps {
    private static final File JSON_RESPONSE_SCHEME_FILE = getJsonGeocodingResponseSchemeFile();

    public static File getJsonGeocodingResponseSchemeFile(){
        try {
            URL filepath = ResponseSteps.class.getClassLoader().getResource(AppSettings.JSON_GEOCODING_RESPONSE_SCHEMA_FILE);
            URLDecoder.decode(filepath.getFile(), "UTF-8");

            return new File(URLDecoder.decode(filepath.getFile(), "UTF-8"));
        }catch(Exception e){
            throw new RuntimeException("cannot get scheme file");
        }
    }


    @Then("response should have valid JSON scheme")
    public void validateJSONScheme() throws Exception{
       String jsonResponse = APIManager.getInstance().getResponseContent();
       Assert.assertTrue("response JSON has wrong scheme: " + jsonResponse,JSONValidator.isJsonValid(JSON_RESPONSE_SCHEME_FILE, jsonResponse));

    }

    @Then("^json response should have location coordinates (.*), (.*)$")
    public void validateCoordinates(String expectedLat, String expectedLng) throws Exception{
        String response = APIManager.getInstance().getResponseContent();

        JsonNode node = JsonLoader.fromString(response);
        JsonNode locationNode = node.findValue("geometry").findValue("location");

        String foundLat = locationNode.findValue("lat").asText();
        String foundLng = locationNode.findValue("lng").asText();

        Assert.assertEquals("found wrong lat coordinate",expectedLat,  foundLat);
        Assert.assertEquals("found wrong lng coordinate",expectedLng,  foundLng);
    }



}
