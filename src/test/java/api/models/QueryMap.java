package api.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.Map;

public interface QueryMap {

    default Map queryMap() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> result = mapper.convertValue(this, Map.class);
        result.values().removeAll(Collections.singleton(null));
        return result;
    }

}
