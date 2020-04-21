package testApp;

import org.apache.commons.validator.routines.UrlValidator;

public class TestsConfig {

    private String endpoint;

    public TestsConfig(String args[]) throws Exception {
        this.endpoint = args[0];
        this.setEndpoint(args[0]);
    }

    public void setEndpoint(String endpoint) throws Exception {

        String[] schemes = {"http","https"};

        UrlValidator urlValidator = new UrlValidator(schemes);
        Boolean isValidUrl = endpoint.contains("localhost") || urlValidator.isValid(endpoint);

        if (!isValidUrl){
            throw new Exception("Invalid URL");
        }

        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }

}
