package suiteb;

import dataProvider.TestDataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

public class TestBB extends TestBase {
    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataSuiteB")
    public void testBB(String username, String password) throws InterruptedException {
        log("Starting BB");
        log(username+" "+password);
        Thread.sleep(3000);
        log("Ending BB");
    }
}
