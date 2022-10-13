package reco.core;

/**
 * 构造器参数
 * @author Tommy.Z
 * @date 2022年10月13日 09:08
 */
public class BuilderParam {
    private String name;
    private Type type;
    private String desc;
    private String[] values;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public enum Type{
        LIST,STRING,INT,FLOAT,ENUM,TEMPLATE
    }

    public BuilderParam(String name, Type type, String desc, String[] values) {
        this.name = name;
        this.type = type;
        this.desc = desc;
        this.values = values;
    }
}
