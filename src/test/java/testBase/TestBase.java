package testBase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import reports.ExtentManager;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public ExtentReports rep;
    public ExtentTest test;
    public SoftAssert softAssert;
    public String browser;
    public WebDriver driver;

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

        //System.out.println("The total"+context.getAllTestMethods().length);

        String groupNames[] = context.getAllTestMethods()[0].getGroups();
        String browserGroup = "";
        for (String g : groupNames) {
            if (g.startsWith("browsergroup")) {
                browserGroup = g;
                break;
            }
        }
        browser = context.getCurrentXmlTest().getParameter(browserGroup);
        System.out.println("The browser group name is " + browser);

    }

    @AfterMethod(alwaysRun = true)
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

    public WebDriver launchBrowser(String browserName) {
        if (browserName.equals("Firefox")) {

            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\drivers\\geckodriver.exe");
            System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "logs\\firefox.log");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            firefoxOptions.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");

            ProfilesIni profilesIni = new ProfilesIni();
            FirefoxProfile firefoxProfileDev = profilesIni.getProfile("SeDev");
            firefoxProfileDev.setPreference("dom.webnotifications.enabled", false);
            firefoxProfileDev.setAcceptUntrustedCertificates(true);
            firefoxProfileDev.setAssumeUntrustedCertificateIssuer(false);

            firefoxOptions.setProfile(firefoxProfileDev);
            driver = new FirefoxDriver(firefoxOptions);

        } else if (browserName.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\drivers\\chromedriver.exe");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "logs\\chrome.log");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("ignore-certificate-errors");
            chromeOptions.addArguments("user-data-dir=C:\\Users\\SANTOS~1\\AppData\\Local\\Temp\\scoped_dir13924_901200296\\Profile 1");

            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equals("Edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") +"\\drivers\\msedgedriver.exe");

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--disable-notifications");
            edgeOptions.addArguments("--start-maximized");
            edgeOptions.addArguments("ignore-certificate-errors");

            driver = new EdgeDriver(edgeOptions);
        }
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        return driver;
    }

}
