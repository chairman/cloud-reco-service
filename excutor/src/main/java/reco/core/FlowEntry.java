package reco.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * flow组件，走推荐逻辑的主流程
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
@Component
public class FlowEntry {
    private final Logger logger = LoggerFactory.getLogger(FlowEntry.class);

    @Autowired
    private Rules rules;

    public RecoResponse process(Context context ){
        try {
            List<Rule> list = rules.getRules();
            //遍历规则，匹配，找到对应的执行器组
            for(Rule rule:list){
                if(rule.getSelector().judge(context)){
                    context.setExecutor(rule.getExecutor().megre(context));
                    boolean hasNull = false;
                    if(context.getExecutor() == null) hasNull = true;
                    if(hasNull){
                        context.setExecutor(null);
                    }else {
                        break;
                    }
                }
            }

            if(context.getExecutor() == null) throw new RecoException( "executor is not found" );

            //初始化上下文
            context.getExecutor().init(context);
            //走执行器的process流程
            context.getExecutor().process(context);
            //最终把数据返回出去
            RecoResponse response = new RecoResponse();
            response.setRecoResult(context.getRecoResult());
            //推出去的数据可以做一些打点
            return response;
        }catch (Exception e){
            //出异常也可以打点
            return null;
        }
    }
}
