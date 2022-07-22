package helpers.factories;

import helpers.config.Device;
import helpers.config.TestConfiguration;
import java.util.HashMap;
import java.util.Optional;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CapabilitiesFactory {

    private static final Logger logger = LoggerFactory.getLogger(CapabilitiesFactory.class);

    public static Capabilities getCapabilities(Device device, String testName) {
        DesiredCapabilities capabilities = new DesiredCapabilities(device.getCapabilities());

        String app = Optional.of(System.getenv("APP"))
            .orElseThrow(() -> new RuntimeException("Can't read APP"));

        if (TestConfiguration.getHubName().equals("browserstack")) {
            String buildName = Optional.of(System.getenv("BROWSERSTACK_BUILD_NAME"))
                .orElseThrow(() -> new RuntimeException("Can't read BROWSERSTACK_BUILD_NAME"));
            HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
            browserstackOptions.put("buildName", buildName);
            browserstackOptions.put("sessionName", testName);
            browserstackOptions.put("userName", TestConfiguration.getBSUser());
            browserstackOptions.put("accessKey", TestConfiguration.getBSPassword());
            browserstackOptions.put("appiumVersion", "2.0.0");
            browserstackOptions.put("disableAnimations", "true");
            browserstackOptions.put("idleTimeout", "120");
            capabilities.setCapability("bstack:options", browserstackOptions);
        }

        capabilities.setCapability("app", app);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("autoDismissAlerts", true);
        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("retryBackoffTime", 500);
        capabilities.setCapability("maxRetryCount", 10);
        return capabilities;
    }

}
