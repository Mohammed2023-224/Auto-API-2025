package engine.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import engine.reporter.CustomLogger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;


public class PojoHelpers {
    private static final ObjectMapper objectMapper;

    static {
        objectMapper=new ObjectMapper();
    }

    protected static Object getSingleData(Object data, Class className){
        if (data instanceof LinkedHashMap) {
            CustomLogger.logger.info("Convert linked hash object into object");
            return objectMapper.convertValue(data, className);
        }
        return null;
    }

    protected static List<?> getDataInList(Object data, Class className){
        if (data instanceof List) {
            CustomLogger.logger.info("Convert object into list");
            return ((List<?>) data).stream()
                    .map(obj -> objectMapper.convertValue(obj, className))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
