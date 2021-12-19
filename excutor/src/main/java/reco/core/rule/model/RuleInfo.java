package reco.core.rule.model;

public enum RuleInfo {
    T_BASE_RULES("t_base_rules",RuleConfig.class),
    T_PRE_BASE_RULES("t_pre_base_rules",RuleConfig.class),
    ;

    private String dbName = "";
    private String tableName = "";
    private String ds = "";
    private String tag = "";
    private Class entityClass;
    RuleInfo(String tableName, Class entityClass){
        this.tableName = tableName;
        this.tag = tableName;
        this.entityClass = entityClass;
    }

    RuleInfo(String dbName, String tableName, String ds, String tag, Class entityClass) {
        this.dbName = dbName;
        this.tableName = tableName;
        this.ds = ds;
        this.tag = tag;
        this.entityClass = entityClass;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }
}
