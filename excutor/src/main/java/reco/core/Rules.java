package reco.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import reco.core.rule.RuleService;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class Rules implements InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(Rules.class);

//    @Resource
//    private RuleService ruleService;

    private List<Rule> rules;

    public List<Rule> getRules(){
        return rules;
    }

    public Rules() {
        this.rules = new ArrayList<>();
    }

    private String env = "pre";

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void update(){

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        update();
    }
}
