package helpers.factories;

import static java.text.MessageFormat.format;

import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.config.Device;
import helpers.config.TestConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;

import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public class DriverProvider {

    public static final String ANDROID_DRIVER = "Android";
    public static final String IOS_DRIVER = "iOS";

    private static AppiumDriver create(String driver, URL hubUrl, Capabilities capabilities) {
        switch (driver) {
            case ANDROID_DRIVER:
                SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
                return new AndroidDriver(hubUrl, capabilities);
            case IOS_DRIVER:
                return new IOSDriver(hubUrl, capabilities);
            default:
                throw new RuntimeException(format("Unknown driver: '{0}'", driver));
        }
    }

    public static WebDriver createDriver(String testName) {
        URL hubUrl = TestConfiguration.getHubUrl();
        Device device = TestConfiguration.getDevice();
        Capabilities flutterCapabilities = CapabilitiesFactory.getCapabilities(device, testName);
        AppiumDriver driver = create(device.getDriver(), hubUrl, flutterCapabilities);
        return driver;
    }

}
