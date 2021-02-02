package tests;

import java.net.URL;
import java.util.List;
import java.net.MalformedURLException;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.MobileBy.AccessibilityId;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.className;

public class BrowserStackAndroidTests extends TestBase{

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "qaguru3");
        caps.setCapability("browserstack.key", "PDQAwqS6GqzeNLqsj92r");

        // Set URL of the application under test
        caps.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "Java Android");
        caps.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps);

        // Test case for the BrowserStack sample Android app.
        // If you have uploaded your app, update the test case here.
        AndroidElement searchElement = (AndroidElement) new WebDriverWait(driver, 30).until(
            ExpectedConditions.elementToBeClickable(
                AccessibilityId("Search Wikipedia")));
        searchElement.click();
        AndroidElement insertTextElement = (AndroidElement) new WebDriverWait(driver, 30).until(
              ExpectedConditions.elementToBeClickable(
                  MobileBy.id("org.wikipedia.alpha:id/search_src_text")));
        insertTextElement.sendKeys("BrowserStack");
        Thread.sleep(5000);
        List<AndroidElement> allProductsName = driver.findElementsByClassName(
            "android.widget.TextView");
        assert(allProductsName.size() > 0);

        // Invoke driver.quit() after the test is done to indicate that the test is completed.
        driver.quit();
    }
    @Feature("Android Test")
    @Tag("android")
    @Test
    @DisplayName("Successful search in wikipedia android app ")
    public void androidTests() {
        open();
        $(AccessibilityId("Search Wikipedia")).click();
        $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).setValue("BrowserStack");
        $$(className("android.widget.TextView")).shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}