package reco.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class FlowEntry {
    private final Logger logger = LoggerFactory.getLogger(FlowEntry.class);

    @Autowired
    private Rules rules;

    public RecoResponse process(Context context ){
        try {
            List<Rule> list = rules.getRules();
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

            context.getExecutor().init(context);
            context.getExecutor().process(context);
            RecoResponse response = new RecoResponse();
            response.setRecoResult(context.getRecoResult());
            return response;
        }catch (Exception e){
            return null;
        }
    }
}
