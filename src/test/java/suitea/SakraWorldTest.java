package suitea;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import testBase.TestBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SakraWorldTest extends TestBase {

    @Test
    public void appointmentTest() throws InterruptedException {

        launchBrowser("Chrome");
        log("Opened browser Chrome");
        driver.get(properties.getProperty("url"));
        waitForPageToLoad();
        driver.findElement(By.linkText(properties.getProperty("doctor_name"))).click();
        //Explicite wait
        //Thread.sleep(10000);
        waitForPageToLoad();
        if (isElementPresent(properties.getProperty("req_name")))
            failAndStop("Name field is not present/visible");
        log("Writing name");
        driver.findElement(By.id(properties.getProperty("name_field"))).sendKeys(properties.getProperty("firstName"));
        log("Writing email address");
        driver.findElement(By.xpath(properties.getProperty("email"))).sendKeys(properties.getProperty("emailAddress"));
        log("Writing phone Number");
        driver.findElement(By.xpath(properties.getProperty("phone"))).sendKeys(properties.getProperty("phoneNumber"));
        WebElement gender = driver.findElement(By.id(properties.getProperty("genderName")));

        Select s = new Select(gender);
        s.selectByVisibleText("Male");

        Thread.sleep(5000);
        driver.quit();

    }

    public boolean isElementPresent(String locator) {
        WebElement e = null;
        try {
            e = driver.findElement(By.id(locator));
        } catch (Exception exception) {
            log("Exception while extracting objects " + exception.getMessage());
            return false;
        }

        if (!e.isDisplayed()) {
            log("Element got extracted " + e.isDisplayed());
        }
        return true;
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
            Thread.sleep(time * 2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}