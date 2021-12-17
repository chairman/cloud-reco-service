package reco.core;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂
 */
public class Registry {
    private final Logger logger = LoggerFactory.getLogger(Registry.class);

    volatile private static Registry instance = null;

    private Map<String,Builder<ParameterizedExecutor>> executorMap = new HashMap<>();

    public Map<String, Builder<ParameterizedExecutor>> getExecutorMap() {
        return executorMap;
    }

    public void addExecutor(Builder<ParameterizedExecutor> builder) {
        getInstance().executorMap.put(builder.getName(), builder);
    }

    public static ParameterizedExecutor parseExecutor(JsonNode params){
        String key = params.get("type").asText();
        if (key == null) return null;
        Builder<ParameterizedExecutor> builder = getInstance().getExecutorMap().get(key);
        if(builder==null) return null;
        return builder.build(params);
    }

    private Map<String,Builder<Selector>> selectorMap = new HashMap<>();

    public static void addSelector(Builder<Selector> builder){
        getInstance().selectorMap.put(builder.getName(), builder);
    }

    public Map<String, Builder<Selector>> getSelectorMap() {
        return selectorMap;
    }

    public static Selector parseSelector(JsonNode params){
        String key = params.fieldNames().next();
        if (key == null) return null;
        Builder<Selector> builder = getInstance().getSelectorMap().get(key);
        if(builder==null) return null;
        return builder.build(params);
    }

    private static Registry getInstance(){
        if(instance == null){
            synchronized (Registry.class){
                if(null == instance){
                    instance = new Registry();
                    instance.init();
                }
            }
        }
        return instance;
    }

    private static String[] allClass = new String[]{
            "reco.core.excutor.UvExcutor",
            "reco.core.excutor.Order"
    };

    private void init(){
        for (String className:allClass){
            try {
                Class.forName(className);
            }catch (ClassNotFoundException e){
                logger.error("core init err",e);
            }
        }
    }
}
