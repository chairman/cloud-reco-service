package reco.core.rule.dao;


import reco.core.rule.model.RuleConfig;

import java.util.List;

public interface RulesRepository {
    public List<RuleConfig> list();
    public List<RuleConfig> listPre();
    public void addRule(RuleConfig ruleConfig);
    public void updateRule(RuleConfig ruleConfig);
    public void deleteRule(int ruleId);
}
