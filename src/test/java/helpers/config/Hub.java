package helpers.config;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import java.util.HashMap;
import lombok.Data;

@Data
public class Hub {

    @JsonProperty("url")
    private String url;
    @JsonProperty("devices")
    private HashMap<String, Device> devices;

}
