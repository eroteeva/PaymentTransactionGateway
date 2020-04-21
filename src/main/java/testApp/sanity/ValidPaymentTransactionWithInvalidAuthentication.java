package testApp.sanity;

import testApp.ExecuteTransactionException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ValidPaymentTransactionWithInvalidAuthentication extends AbstractTransactionTest {

    private final String jsonInputString = "{\n" +
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

    @Override
    public void execute(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        con.setRequestProperty("Authorization", "Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQt");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(jsonInputString.getBytes());
        os.flush();
        os.close();

        int status = con.getResponseCode();
        System.out.println("POST Response Code :: " + status);
        if (status != 401) {
            throw new ExecuteTransactionException("When sending a valid payment transaction with invalid authentication response status 401 was expected, but found: " + status);
        }
    }
}
