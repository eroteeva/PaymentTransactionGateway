package testApp.sanity;

import java.io.IOException;

public abstract class AbstractTransactionTest {

    String endpoint;

    public abstract void execute(String endpoint) throws IOException;

}
