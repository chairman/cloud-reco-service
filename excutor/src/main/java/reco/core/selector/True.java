package reco.core.selector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import reco.core.*;

import java.util.List;

public class True implements Selector {
    private String key;
    private Object value;

    public True(){}

    @Override
    public boolean judge(Context context) {
        return true;
    }

    static {
        Registry.addSelector(new Builder<Selector>() {
            @Override
            public List<BuilderParam> getParams() {
                return null;
            }

            @Override
            public Selector build(JsonNode params) {
                return new True();
            }

            @Override
            public String getName() {
                return "$true";
            }
        });
    }
}
