package reco.core.excutor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.*;

import java.util.Arrays;
import java.util.List;

public class UvExcutor extends AbstractExcutor {
    private final Logger logger = LoggerFactory.getLogger(UvExcutor.class);
    private String id;
    private String type;

    public UvExcutor(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public void process(Context context) {
        logger.info("打印一下日志");
        context.getRedisTemplate().opsForValue().set("zcw","cccc");
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

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

                    return (context) -> {
                        try {
                            return new UvExcutor(id,type);
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
