package reco.core.rule;

import reco.core.rule.model.RuleConfig;

import java.util.List;

public class RuleServiceImpl implements RuleService{
    @Override
    public List<RuleConfig> list() {
        return null;
    }

    @Override
    public List<RuleConfig> listPre() {
        return null;
    }

    @Override
    public void addRule(String selector, String executor, int order, String description, String user) {

    }

    @Override
    public void updateRule(int ruleId, String selector, String executor, int order, String description, String user) {

    }

    @Override
    public void deleteRule(int ruleId) {

    }
}
