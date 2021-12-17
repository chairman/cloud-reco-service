package reco.core.rule.model;

public enum RuleInfo {
    T_BASE_RULES("t_base_rules",RuleConfig.class),
    ;

    private String dbName = "";
    private String tableName = "";
    private String ds = "";
    private String tag = "";
    private Class entityClass;
    RuleInfo(String dbName, Class entityClass){
        this.dbName = dbName;
        this.entityClass = entityClass;
    }
    RuleInfo(String dbName, String tableName, String ds, String tag, Class entityClass) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.ds = ds;
        this.tag = tag;
        this.entityClass = entityClass;
    }
}
