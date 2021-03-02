package suiteb;

import dataProvider.TestDataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

public class TestB extends TestBase {

    @Test(groups = {"sanity","Browser"},dataProviderClass = TestDataProvider.class, dataProvider = "dataSuiteB")
    public void testB(String username, String password) throws InterruptedException {
        log("Starting B");
        log(username + " " + password);
        Thread.sleep(3000);
        log("Ending B");
    }
}
