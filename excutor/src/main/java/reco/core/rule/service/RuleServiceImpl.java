package reco.core.rule.service;

import org.springframework.stereotype.Service;
import reco.core.rule.dao.RulesRepository;
import reco.core.rule.model.RuleConfig;
import reco.core.rule.model.RuleInfo;
import javax.annotation.Resource;
import java.util.List;

@Service
public class RuleServiceImpl implements RuleService{

    @Resource
    private RulesRepository rulesRepository;

    @Resource
    private IdService idService;

    @Override
    public List<RuleConfig> list() {
        return rulesRepository.list();
    }

    @Override
    public List<RuleConfig> listPre() {
        return rulesRepository.listPre();
    }

    @Override
    public void addRule(String selector, String executor, int order, String description, String user) {
        int ruleId = idService.getNext(RuleInfo.T_BASE_RULES.getTableName());
        RuleConfig ruleInfo = new RuleConfig();
        ruleInfo.setRuleId(ruleId);
        ruleInfo.setSelector(selector);
        ruleInfo.setExecutor(executor);
        ruleInfo.setOrder(order);
        ruleInfo.setDescription(description);
        ruleInfo.setOp(user);
        rulesRepository.addRule(ruleInfo);
    }

    @Override
    public void updateRule(int ruleId, String selector, String executor, int order, String description, String user) {
        RuleConfig ruleInfo = new RuleConfig();
        ruleInfo.setRuleId(ruleId);
        ruleInfo.setSelector(selector);
        ruleInfo.setExecutor(executor);
        ruleInfo.setOrder(order);
        ruleInfo.setDescription(description);
        ruleInfo.setOp(user);
        rulesRepository.updateRule(ruleInfo);
    }

    @Override
    public void deleteRule(int ruleId) {
        rulesRepository.deleteRule(ruleId);
    }
}
