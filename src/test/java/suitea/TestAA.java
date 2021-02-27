package suitea;

import dataProvider.TestDataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

public class TestAA extends TestBase {

    @Test(dataProviderClass = TestDataProvider.class, dataProvider = "dataSuiteA")
    public void testAA(String username, String password) throws InterruptedException {
        log("Starting AA");
        log("Logging into a webpage");
        if(!"Text1".equals("Text"))
            softAssert("Text doesn't match");
        //softAssert.assertEquals("Title","Title1");
        log(username + " " + password);
        //Assert.fail("Failing the Test case on purpose");
        Thread.sleep(3000);
        log("Ending AA");
        //Assert.fail("Failing the Test case on purpose");
        softAssert.assertAll();

    }
}
