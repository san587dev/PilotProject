package suitea;

import com.aventstack.extentreports.Status;
import dataProvider.TestDataProvider;
import org.testng.annotations.Test;
import testBase.TestBase;

public class TestA extends TestBase {

    @Test(groups = {"sanity","Browser"},dataProviderClass = TestDataProvider.class, dataProvider = "dataSuiteA")
    public void testA(String username, String password) throws InterruptedException {
        log("Starting A");
        log(username + " " + password);
        Thread.sleep(3000);
        log("Ending A");
    }
}
