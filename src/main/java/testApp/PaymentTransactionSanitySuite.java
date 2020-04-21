package testApp;

import com.google.gson.Gson;
import testApp.sanity.AbstractTransactionTest;
import testApp.sanity.ValidPaymentTransaction;
import testApp.sanity.ValidVoidTransaction;

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

    private void sendValidPaymentTransaction() throws IOException {

        URL url = new URL(testsConfig.getEndpoint());
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        con.setRequestProperty("Authorization", "Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        String jsonInputString = "{\n" +
                "\t\"payment_transaction\": {\n" +
                "      \"card_number\": \"4200000000000000\",\n" +
                "      \"cvv\": \"123\",\n" +
                "      \"expiration_date\": \"06/2019\",\n" +
                "      \"amount\": \"500\",\n" +
                "      \"usage\": \"Coffeemaker\",\n" +
                "      \"transaction_type\": \"sale\",\n" +
                "      \"card_holder\": \"Panda Panda\",\n" +
                "      \"email\": \"panda@example.com\",\n" +
                "      \"address\": \"Panda Street, China\"\n" +
                "    }\n" +
                "}";

        OutputStream os = con.getOutputStream();
        os.write(jsonInputString.getBytes());
        os.flush();
        os.close();

        int status = con.getResponseCode();
        System.out.println("GET Response Code :: " + status);
        if (status == HttpURLConnection.HTTP_OK) {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            Gson gson = new Gson();
            TransactionResponse transactionResponse = gson.fromJson(response.toString(), TransactionResponse.class);
            if ("approved".equals(transactionResponse.getStatus())) {
                System.out.println("The transaction has been approved.");
            }
        }
    }
}
