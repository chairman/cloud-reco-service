package reco.core.excutor;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.*;

import java.util.Arrays;
import java.util.List;

/**
 * 相似推荐算法出来的数据获取执行器（关联推荐算法使用）
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public class SvExcutor extends AbstractExcutor {
    private final Logger logger = LoggerFactory.getLogger(SvExcutor.class);
    private String id;
    private String type;

    public SvExcutor(String id, String type) {
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
                    //还可以有更多别的个性化参数，例如redis中key的前缀(xxx+bookid),自定义algoid、自定义执行器名称...等

                    return (context) -> {
                        try {
                            return new SvExcutor(id,type);
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
