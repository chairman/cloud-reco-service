package reco.core;

import lombok.Data;

@Data
public class AddRuleRequest {
    private String selector;
    private String executor;
    private Integer order;
    private String description;
    private String user;
}
