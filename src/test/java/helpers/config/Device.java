package helpers.config;

import io.qameta.allure.internal.shadowed.jackson.annotation.JsonProperty;
import java.util.HashMap;
import lombok.Data;


@Data
public class Device {

    @JsonProperty("driver")
    private String driver;
    @JsonProperty("capabilities")
    private HashMap<String, String> capabilities;

}
