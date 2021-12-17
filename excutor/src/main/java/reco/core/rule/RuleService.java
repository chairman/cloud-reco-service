package reco.core.rule;

import reco.core.rule.model.RuleConfig;
import java.util.List;

public interface RuleService {
    List<RuleConfig> list();
    List<RuleConfig> listPre();
    void addRule(String selector,String executor,int order,String description,String user);
    void updateRule(int ruleId,String selector,String executor,int order,String description,String user);
    void deleteRule(int ruleId);
}
