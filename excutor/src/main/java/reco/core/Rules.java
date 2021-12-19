package reco.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import reco.core.rule.model.RuleConfig;
import reco.core.rule.service.RuleService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class Rules implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(Rules.class);

    @Resource
    private RuleService ruleService;

    private List<Rule> rules;

    public List<Rule> getRules(){
        return rules;
    }

    public Rules() {
        this.rules = new ArrayList<>();
    }

    private String env = "online";

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void update(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Rule> rules = new ArrayList<>();
        List<RuleConfig> ruleConfigs = new ArrayList<>();
        if(getEnv().equals("online")){
            ruleConfigs = ruleService.list();
        }else {
            ruleConfigs = ruleService.listPre();
        }
        if(CollectionUtils.isEmpty(ruleConfigs)) return;
        for (RuleConfig config:ruleConfigs){
            try {
                String selectorConfig = config.getSelector();
                String exectorConfig = config.getExecutor();
                Rule rule = new Rule();
                rule.setId(config.getRuleId());
                rule.setSelector(Registry.parseSelector(objectMapper.readTree(selectorConfig)));
                rule.setExecutor(Registry.parseExecutor(objectMapper.readTree(exectorConfig)));
                if(rule.getExecutor() == null){
                    logger.error("update rule"+config.getRuleId()+"err");
                }else {
                    rules.add(rule);
                }
            }catch (Exception e){
                logger.error("update rule"+config.getRuleId()+"err");
            }
        }
        this.rules = rules;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        update();
    }
}
