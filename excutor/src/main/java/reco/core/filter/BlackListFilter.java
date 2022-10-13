package reco.core.filter;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.*;

import java.util.Arrays;
import java.util.List;

/**
 * 针对黑名单的过滤（黑名单可以放MYSQL、配置中心等方式）
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public class BlackListFilter extends AbstractFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String id;
    private String type;

    public BlackListFilter(String id, String type) {
        this.id = id;
        this.type = type;
    }

    /**
     * 初始化实现，例如把ID加到内存里面来
     * @param context
     */
    @Override
    public void init(Context context){

    }

    /**
     * 针对单个id的过滤
     * @param id
     * @return
     */
    @Override
    public boolean filter(Object id) {
        //实现具体过滤逻辑
        return false;
    }

    /**
     * 针对批量id的预过滤，ID数组跟结果数据是对应的，结果数据是一组布尔值
     * @param id
     * @return
     */
    @Override
    public boolean[] preFilter(Object[] id) {
        //实现具体过滤逻辑
        return new boolean[0];
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
     * 静态代码块在load CLASS到JVM之后便创建过滤器并挂到filterMap（ HashMap）里面
     */
    static {
        Registry.addFilter(new Builder<ParameterizedFilter>() {
            private final Logger logger = LoggerFactory.getLogger(getClass());
            @Override
            public List<BuilderParam> getParams() {
                return Arrays.asList(
                        new BuilderParam("id", BuilderParam.Type.STRING, "", null),
                        new BuilderParam("type", BuilderParam.Type.STRING, "", null)
                );
            }

            @Override
            public ParameterizedFilter build(JsonNode params) {
                try {
                    String id = params.get("id").asText();
                    String type = params.get("type").asText();

                    return (context) ->{
                        try {
                            return new BlackListFilter(id,type);
                        }catch (Exception e){
                            return null;
                        }
                    } ;
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public String getName() {
                return null;
            }
        });
    }
}
