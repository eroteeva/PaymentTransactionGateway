package testApp;

import com.google.gson.Gson;
import testApp.sanity.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PaymentTransactionSanitySuite {

    TestsConfig testsConfig;

    public PaymentTransactionSanitySuite(TestsConfig testsConfig) {
        this.testsConfig = testsConfig;
    }

    protected void executeSanityTests() throws IOException {

        String endpoint = testsConfig.getEndpoint();
        List<AbstractTransactionTest> transactionTests = new ArrayList<>();
        transactionTests.add(new ValidPaymentTransaction());
        transactionTests.add(new ValidVoidTransaction());
        transactionTests.add(new ValidPaymentTransactionWithInvalidAuthentication());
        transactionTests.add(new VoidTransactionToNonexistentPayment());

        List<String> errors = new ArrayList<>();

        for (AbstractTransactionTest transactionTest : transactionTests){
            try {
                transactionTest.execute(endpoint);
            } catch (ExecuteTransactionException e) {
                errors.add(e.getMessage());
            }
        }

        for (String error : errors) {
            System.out.println(error + "\n");
        }
    }
}
