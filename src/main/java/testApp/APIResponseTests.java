package testApp;

public class APIResponseTests {

    public static void main(String args[]) throws Exception {

        TestsConfig testsConfig = new TestsConfig(args);
        PaymentTransactionSanitySuite paymentTransactionSanitySuite = new PaymentTransactionSanitySuite(testsConfig);
        paymentTransactionSanitySuite.executeSanityTests();

    }

}
