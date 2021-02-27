package testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import reports.ExtentManager;

import java.util.Locale;

public class TestBase {
    public ExtentReports rep;
    public ExtentTest test;
    public SoftAssert softAssert;
    public String browser;

    @BeforeMethod(alwaysRun = true)
    public void init(ITestContext context, ITestResult result) {
        System.out.println("___________________________________________________Before Method");
        System.out.println(result.getMethod().getMethodName().toUpperCase());
        rep = ExtentManager.getReports();
        test = rep.createTest(result.getMethod().getMethodName().toUpperCase());
        result.setAttribute("reporter", test);
        softAssert = new SoftAssert();
//        browser = context.getCurrentXmlTest().getParameter("Browser");
//        System.out.println(browser);

//        String arr[] = context.getIncludedGroups();
//        System.out.println(arr[arr.length]);

    }

    @AfterMethod
    public void quit() {
        System.out.println("___________________________________________________After Method");
        rep.flush();
    }

    public void log(String message) {
        System.out.println(message);
        test.log(Status.INFO, message);
    }

    public void logFailure(String message) {
        System.out.println(message);
        test.log(Status.FAIL, message);
    }

    public void failAndStop(String message) {
        //System.out.println(message);
        softAssert(message);
        softAssert.assertAll();
    }

    public void softAssert(String msg) {//fail in testng as well as extent - but continue
        logFailure(msg);// extent
        softAssert.fail(msg); // testng
        // take screenshot as well - put in reports
    }

}
