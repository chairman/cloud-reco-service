package reco.core.excutor.online;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.*;
import reco.core.excutor.AbstractExcutor;

import java.util.Arrays;
import java.util.List;

/**
 * 在线排序的执行器，会调用到tensorflow-java加载的排序模型（异步加载到JVM），主要服务的算法功能为近似最近邻搜索算法
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public class OnlineSortingExcutor extends AbstractExcutor {
    private final Logger logger = LoggerFactory.getLogger(OnlineSortingExcutor.class);
    private String id;
    private String type;

    public OnlineSortingExcutor(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public void process(Context context) {
        logger.info("打印一下日志");
//        context.getRedisTemplate().opsForValue().set("zcw","cccc");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    /**
     * 静态代码块在load CLASS到JVM之后便创建执行器并挂到HashMap里面
     */
    static {
        Registry.addExecutor(new Builder<ParameterizedExecutor>() {
            private final Logger logger = LoggerFactory.getLogger(getClass());

            @Override
            public List<BuilderParam> getParams() {
                return Arrays.asList(
                    new BuilderParam("id", BuilderParam.Type.STRING, "", null),
                    new BuilderParam("type", BuilderParam.Type.STRING, "", null)
                );
            }

            @Override
            public ParameterizedExecutor build(JsonNode params) {
                try {
                    String id = params.get("id").asText();
                    String type = params.get("type").asText();
                    //还可以有更多别的个性化参数，例如redis中key的前缀(xxx+uid),自定义algoid、自定义执行器名称...等

                    return (context) -> {
                        try {
                            return new OnlineSortingExcutor(id,type);
                        } catch (Exception e1) {
                            return null;
                        }
                    };
                } catch (Exception e) {
                    return null;
                }
            }

            @Override
            public String getName() {
                                     return "uvexecutor";
                                 }
        });
    }
}