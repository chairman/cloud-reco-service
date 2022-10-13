package reco.core.excutor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.*;

import java.util.Arrays;
import java.util.List;

/**
 * 顺序执行器(主要为了组织执行器的逻辑)
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public class Order extends AbstractExcutor{
    private final Logger logger = LoggerFactory.getLogger(Order.class);

    private Executor[] executors;

    private String id;

    private String type;

    public Order(String id,String type,Executor[] executors) {
        this.id = id;
        this.type = type;
        this.executors = executors;
    }

    /**
     * 遍历子执行器，并逐个初始化
     * @param context
     */
    @Override
    public void init(Context context){
        if(executors!=null){
            for (Executor executor:executors){
                executor.init(context);
            }
        }
    }

    /**
     * 遍历子执行器，并逐个cleanup
     * @param context
     */
    @Override
    public void cleanup(Context context){
        if(executors!=null){
            for (Executor executor:executors){
                executor.cleanup(context);
            }
        }
    }

    /**
     * 遍历子执行器，并逐个走子执行器里面的逻辑
     * @param context
     */
    @Override
    public void process(Context context) {
        if(executors!=null){
            for (Executor executor:executors){
                executor.process(context);
            }
        }
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
                                             new BuilderParam("id",BuilderParam.Type.STRING,"",null),
                                             new BuilderParam("children",BuilderParam.Type.STRING,"",null)
                                     );
                                 }

                                 @Override
                                 public ParameterizedExecutor build(JsonNode params) {
                                     try {
                                         String id = params.get("id").asText();
                                         String type = params.get("type").asText();

                                         ArrayNode list = (ArrayNode) params.withArray("children");
                                         ParameterizedExecutor[] executors = new ParameterizedExecutor[list.size()];
                                         for (int i=0;i<list.size();i++){
                                             JsonNode toJsonNode = list.get(i);
                                             executors[i] = Registry.parseExecutor(toJsonNode);
                                             if (executors[i]==null) return null;
                                         }

                                         return (context) -> {
                                             try {
                                                 Executor[] executors1 = new Executor[executors.length];
                                                 for (int i=0;i<executors.length;i++){
                                                     executors1[i] = executors[i].megre(context);
                                                 }
                                                 return new Order(id,type,executors1);
                                             }catch (Exception e1){
                                                 return null;
                                             }
                                         };
                                     }catch (Exception e){
                                        return null;
                                     }
                                 }

                                 @Override
                                 public String getName() {
                                     return "order";
                                 }
                             }

        );
    }
}
