package tests;

import static helpers.factories.DriverProvider.createDriver;

import com.codeborne.selenide.WebDriverRunner;
import helpers.config.TestConfiguration;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    TestConfiguration configuration = TestConfiguration.getInstance();

    public static <T> T skipIfException(Callable<T> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new SkipException(e.getMessage(), e.getCause());
        }
    }

    public static void skipIfException(Runnable run) {
        try {
            run.run();
        } catch (Exception e) {
            throw new SkipException(e.getMessage(), e.getCause());
        }
    }

    @BeforeMethod(description = "Запустить приложение")
    public void openApp(Method test) {
        WebDriver driver = createDriver(test.getName());
        WebDriverRunner.setWebDriver(driver);
    }

    @AfterMethod(description = "Закрыть приложение")
    public void closeApp() {
        if (WebDriverRunner.hasWebDriverStarted()) {
            WebDriverRunner.closeWebDriver();
        }
    }

}
