package testApp.sanity;

import com.google.gson.Gson;
import testApp.TransactionResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ValidVoidTransaction extends AbstractTransactionTest {

    private final String jsonInputString = "{\n" +
            "    \"payment_transaction\": {\n" +
            "      \"reference_id\": \"0e08644635ccb520c2eeb54f33865660\",\n" +
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
