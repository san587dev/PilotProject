package suitea;

import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SakraWorldTest extends TestBase {

    @Test
    public void appointmentTest() {
        Properties properties = null;

        try {
            properties = new Properties();
            FileInputStream fs = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//project.properties");
            properties.load(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("url"));

        driver = launchBrowser("Chrome");
        driver.get("https://www.sakraworldhospital.com/request-appointment.php");

    }
}
