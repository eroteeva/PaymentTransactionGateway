package testApp;

import org.apache.commons.validator.routines.UrlValidator;

public class TestsConfig {

    private String endpoint;
    private String transactionType;

    public TestsConfig(String args[]) throws Exception {
        this.endpoint = args[0];
        this.transactionType = args[1];
        this.setEndpoint(args[0]);
    }

    public void setEndpoint(String endpoint) throws Exception {

        String[] schemes = {"http","https"};

        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(endpoint)){
            throw new Exception("Invalid URL");
        }

        this.endpoint = endpoint;
    }

    public String getEndpoint(){
        return endpoint;
    }

    public String getTransactionType(){
        return transactionType;
    }
}
