package reco.core;

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
}
