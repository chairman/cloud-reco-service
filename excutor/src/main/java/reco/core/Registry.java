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

    public static void addExecutor(Builder<ParameterizedExecutor> builder) {
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

    private Map<String,Builder<ParameterizedFilter>> filterMap = new HashMap<>();

    public static Map<String,Builder<ParameterizedFilter>> getFilterMap(){
        return getInstance().filterMap;
    }

    public static void addFilter(Builder<ParameterizedFilter> builder){
        getInstance().filterMap.put(builder.getName(),builder);
    }

    private static Registry getInstance(){
        if(instance == null){
            //检测是否为空，如果为空则进入创建步骤，否则返回
            // （这里面有一个坑：如果没使用volatie修饰instance，A线程的里面做了指令的重排序，
            // 那么B得到的可能是一个非空未初始化的变量，返回回去导致调用端的一些异常情况,volatie避免了这种情况的发生）
            synchronized (Registry.class){
                if(null == instance){
                    //防止A线程初始化之后，B获得类锁再次进来，又做了下面两步
                    instance = new Registry();
                    //里面具体的类还是会有重排序的问题，有可能reco.core.excutor.UvExcutor...等还未初始化，所以在外层需要做判空
                    instance.init();
                }
            }
        }
        return instance;
    }

    private static String[] allClass = new String[]{
            "reco.core.excutor.UvExcutor",
            "reco.core.excutor.Order",
            "reco.core.selector.And",
            "reco.core.selector.Eq",
            "reco.core.selector.Gt",
            "reco.core.selector.Gte",
            "reco.core.selector.Lt",
            "reco.core.selector.Lte",
            "reco.core.selector.Or",
            "reco.core.selector.True"
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
