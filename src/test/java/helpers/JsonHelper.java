package helpers;

import static java.text.MessageFormat.format;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.JsonNode;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JsonHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readFromFile(String file, Class<T> clazz) {
        try {
            URL ioStream = JsonHelper.class.getClassLoader().getResource(file);
            if (ioStream == null) {
                String e = format("Cannot read file: {0}", file);
                throw new RuntimeException(e);
            }
            return mapper.readValue(ioStream, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readFromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T readFromJson(String json, Type type) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructType(type));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> String pretty(T value) {
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static <T> String prettyString(T value) {
        String jsonString = pretty(value)
            .replaceAll("\\\\+", "")
            .replaceAll("\"\\{", "{")
            .replaceAll("}\"", "}");
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(
                mapper.readTree(jsonString)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Map<?, ?> toMap(T value) {
        if (value instanceof List) {
            AtomicInteger index = new AtomicInteger();
            return ((List<T>) value).stream()
                .collect(Collectors.toMap(
                        item -> index.decrementAndGet(),
                        item -> mapper.convertValue(item, Map.class)
                    )
                );

        } else {
            return mapper.convertValue(value, Map.class);
        }
    }

    public static JsonNode readNode(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
