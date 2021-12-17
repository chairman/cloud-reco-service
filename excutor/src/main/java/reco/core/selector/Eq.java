package reco.core.selector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import reco.core.*;

import java.util.List;

public class Eq implements Selector {
    private String key;
    private Object value;

    private Eq(String key,Object value){
        this.key = key;
        this.value = value;
    }

    @Override
    public boolean judge(Context context) {
        Object current = context.getState().getValue(this.key);
        if(current==null) return false;
        return String.valueOf(current).equals(String.valueOf(this.value));
    }

    private Selector[] selectors;

    private Eq(Selector[] selectors){
        this.selectors = selectors;
    }

    static {
        Registry.addSelector(new Builder<Selector>() {
            @Override
            public List<BuilderParam> getParams() {
                return null;
            }

            @Override
            public Selector build(JsonNode params) {
                ArrayNode nodes = (ArrayNode)params.withArray(getName());
                return new Eq(nodes.get(0).asText(),toObject(nodes.get(1)));
            }

            public Object toObject(JsonNode node){
                if(node.isInt()){
                    return node.asInt();
                }if(node.isLong()){
                    return node.asLong();
                }if(node.isTextual()){
                    return node.asText();
                }if(node.isDouble()){
                    return node.asDouble();
                }else {
                    return node.isBoolean() ? node.asBoolean() : null;
                }
            }

            @Override
            public String getName() {
                return "$eq";
            }
        });
    }
}
