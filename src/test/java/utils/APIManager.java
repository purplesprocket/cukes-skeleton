package utils;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.Map;

public class APIManager {
    private static APIManager apiManager;

    private String response;

    private APIManager(){}

    public static APIManager getInstance(){
        if (apiManager == null){
            apiManager = new APIManager();
        }
        return apiManager;
    }

    public String getResponseContent(){
        return response;
    }

    public void sendRequest(OutputType outputType, Map<String, String> parameters){
        try {
            response = Request.Get(getRequestURI(outputType, parameters)).execute().returnContent().asString();

        }catch(Exception e){
            throw new RuntimeException("cannot send request", e);
        }
    }

    private URI getRequestURI(OutputType outputType, Map<String, String> parameters)  throws Exception{
        URIBuilder uriBuilder = new URIBuilder().
                setScheme(Properties.getAPIUrlScheme()).
                setHost(Properties.getAPIHost()).
                addParameter("key", Properties.getAppKey());

        parameters.forEach(uriBuilder::addParameter);

        switch(outputType){
            case XML:
                uriBuilder.setPath(Properties.getAPIUrlPath() + "/" + AppSettings.XML_OUTPUT_REQUEST_PARAMETER);
                break;
            case JSON:
                uriBuilder.setPath(Properties.getAPIUrlPath() + "/" + AppSettings.JSON_OUTPUT_REQUEST_PARAMETER);
                break;
        }
        return uriBuilder.build();
    }


}
