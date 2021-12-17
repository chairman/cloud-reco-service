package reco.core.selector;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import reco.core.*;

import java.util.List;

public class Or implements Selector {

    @Override
    public boolean judge(Context context) {
        if(selectors.length == 0) return true;
        for (Selector selector:selectors){
            if(selector.judge(context)) return true;
        }
        return false;
    }

    private Selector[] selectors;

    private Or(Selector[] selectors){
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
                Selector[] selectors = new Selector[nodes.size()];
                for (int i = 0;i<nodes.size();i++){
                    selectors[i] = Registry.parseSelector(nodes.get(i));
                }
                return new Or(selectors);
            }

            @Override
            public String getName() {
                return "$or";
            }
        });
    }
}
