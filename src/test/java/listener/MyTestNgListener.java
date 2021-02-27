package listener;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.SQLOutput;

public class MyTestNgListener implements ITestListener {

    public void onTestFailure(ITestResult result) {
        System.out.println("--------------------------------------Test Failed");
        System.out.println("Failed Test name " + result.getName());
        System.out.println(result.getStatus());
        System.out.println(result.getThrowable().getMessage());
        ExtentTest test = (ExtentTest) result.getAttribute("reporter");
        test.log(Status.FAIL, result.getThrowable().getMessage());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("--------------------------------------Test Passed");
        System.out.println("Pass Test name " + result.getName());
        ExtentTest test = (ExtentTest) result.getAttribute("reporter");
        test.log(Status.PASS, "Test Passed " + result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("--------------------------------------Test Skipped");
        System.out.println("Skipped Test name " + result.getName());
        ExtentTest test = (ExtentTest) result.getAttribute("reporter");
        test.log(Status.SKIP, "Test Skipped " + result.getThrowable().getMessage());

    }
}
