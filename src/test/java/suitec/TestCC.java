package suitec;

import dataProvider.TestDataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

public class TestCC extends TestBase {
    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataSuiteC")
    public void testCC(String username, String password) throws InterruptedException {
        log("Starting CC");
        if(!"Text1".equals("Text"))
            softAssert("Text doesn't match");
        log(username + " " + password);
        Thread.sleep(3000);
        log("Ending CC");
        softAssert.assertAll();
    }
}
