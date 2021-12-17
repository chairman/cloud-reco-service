package reco.core;

import lombok.Data;

@Data
public class UpdateRuleRequest {
    private String selector;
    private String executor;
    private Integer order;
    private String description;
    private String user;
    private Integer ruleId;
}
