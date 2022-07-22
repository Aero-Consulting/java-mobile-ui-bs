package helpers.config;

import com.google.gson.annotations.Expose;
import helpers.JsonHelper;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
import lombok.Data;

@Data
public final class TestConfiguration {

    @Expose
    private static volatile TestConfiguration instance;

    @JsonProperty("api")
    private String apiUrl;
    @JsonProperty("hub")
    private String hubName;
    private Hub hub;
    @JsonProperty("device")
    private String deviceName;
    private Device device;
    @JsonProperty("hubs")
    private HashMap<String, Hub> hubs;

    private TestConfiguration() {
    }

    public static TestConfiguration getInstance() {
        TestConfiguration result = instance;
        if (result != null) {
            return result;
        }
        synchronized (TestConfiguration.class) {
            if (instance == null) {
                instance = new TestConfiguration();
                instance.readConfiguration();
            }
            return instance;
        }
    }

    public static String getHubName() {
        TestConfiguration configuration = getInstance();
        return configuration.hubName;
    }

    public static Hub getHub() {
        TestConfiguration configuration = getInstance();
        return configuration.hub;
    }

    public static Device getDevice() {
        TestConfiguration configuration = getInstance();
        return configuration.device;
    }

    private static Hub getHub(String hub) {
        TestConfiguration configuration = getInstance();
        if (configuration.getHubs().containsKey(hub)) {
            return configuration.getHubs().get(hub);
        } else {
            throw new RuntimeException(String.format("Hub:%s not found", hub));
        }
    }

    public static Device getDeviceForHub(String hubName, String deviceName) {
        Hub hub = getHub(hubName);
        HashMap<String, Device> devices = hub.getDevices();
        if (devices.containsKey(deviceName)) {
            return devices.get(deviceName);
        } else {
            throw new RuntimeException(String.format("Device: %s not found for hub: %s", deviceName, hubName));
        }
    }

    public static URL getHubUrl() {
        Hub hub = getHub();
        try {
            return new URL(hub.getUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    public static String getBSUser() {
        String bsUser = Optional.of(System.getenv("BS_USER"))
            .orElseThrow(() -> new RuntimeException("Can't read BS_USER"));
        return bsUser;
    }

    public static String getBSPassword() {
        String bsPassword = Optional.of(System.getenv("BS_PASSWORD"))
            .orElseThrow(() -> new RuntimeException("Can't read BS_PASSWORD"));
        return bsPassword;
    }

    public static String getApiUrl() {
        TestConfiguration configuration = getInstance();
        return configuration.apiUrl;
    }

    public static boolean isIOS() {
        return getDevice().getDriver().equalsIgnoreCase("ios");
    }

    private void readConfiguration() {
        String fileName = System.getProperty("testconfig", "testconfig.json");
        instance = JsonHelper.readFromFile(fileName, TestConfiguration.class);
        Optional.ofNullable(System.getenv("HUB")).ifPresent(hub -> instance.hubName = hub);
        assert instance.hubName != null;
        assert instance.deviceName != null;
        instance.hub = getHub(instance.hubName);
        instance.device = getDeviceForHub(instance.hubName, instance.deviceName);
    }

}
