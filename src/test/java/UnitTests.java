import org.junit.Assert;
import org.junit.Test;
import testApp.TestsConfig;

public class UnitTests {

    @Test
    public void validEnpointTest() throws Exception {

        String[] args = new String[1];
        args[0] = "http://localhost:3001/payment_transactions/";
        TestsConfig testConfig = new TestsConfig(args);

    }

    @Test
    public void invalidEndpointTest() throws Exception {
        String[] args = new String[1];
        args[0] = "invalidURL";
        String message = "";
        try {
            TestsConfig testConfig = new TestsConfig(args);
        } catch (Exception e) {
            message = e.getMessage();
        }

        Assert.assertEquals("Invalid URL", message);
    }
}
