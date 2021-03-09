package suitea;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SakraWorldTest extends TestBase {

    @Test
    public void appointmentTest() throws InterruptedException {

        driver = launchBrowser("Chrome");
        driver.get(properties.getProperty("url"));
        waitForPageToLoad();
        driver.findElement(By.linkText(properties.getProperty("doctor_name"))).click();
        waitForPageToLoad();
        driver.findElement(By.id(properties.getProperty("name"))).sendKeys("Santosh");
        Thread.sleep(5000);
        driver.quit();

    }

    public void waitForPageToLoad() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int i = 0;

        while (i != 10) {
            String state = (String) js.executeScript("return document.readyState;");
            //System.out.println(state);

            if (state.equals("complete"))
                break;
            else
                wait(2);

            i++;
        }
        // check for jquery status
        i = 0;
        while (i != 10) {

            Long d = (Long) js.executeScript("return jQuery.active;");
            //System.out.println(d);
            if (d.longValue() == 0)
                break;
            else
                wait(2);
            i++;

        }

    }

    public void wait(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}