package testApp.sanity;

import testApp.ExecuteTransactionException;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class VoidTransactionToNonexistentPayment extends AbstractTransactionTest {

    private final String jsonInputString = "{\n" +
            "    \"payment_transaction\": {\n" +
            "      \"reference_id\": \"0e08644635ccb520cdkjfhlghhg33865660\",\n" +
            "      \"transaction_type\": \"void\"\n" +
            "    }\n" +
            "}'";

    @Override
    public void execute(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        con.setRequestProperty("Authorization", "Basic Y29kZW1vbnN0ZXI6bXk1ZWNyZXQta2V5Mm8ybw==");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        OutputStream os = con.getOutputStream();
        os.write(jsonInputString.getBytes());
        os.flush();
        os.close();

        int status = con.getResponseCode();
        System.out.println("POST Response Code :: " + status);
        //check for status 422
        if (status != 422) {
            throw new ExecuteTransactionException("When sending a void transaction to a non-existent payment response status 422 was expected, but found: " + status);
        }
    }

}
