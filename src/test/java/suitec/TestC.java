package suitec;

import dataProvider.TestDataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

public class TestC extends TestBase {
    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataSuiteC")
    public void testC(String username, String password) throws InterruptedException {
        log("Starting C");
        log("Starting a web page");
        log(username + " " + password);
        if(!"a".equals("b"))
            failAndStop("a is not equals to b");
        Thread.sleep(3000);
        log("Ending C");
        softAssert.assertAll();
    }
}
