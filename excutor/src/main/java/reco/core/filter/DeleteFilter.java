package reco.core.filter;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reco.core.Builder;
import reco.core.BuilderParam;
import reco.core.ParameterizedFilter;
import reco.core.Registry;

import java.util.Arrays;
import java.util.List;

public class DeleteFilter extends AbstractFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String id;
    private String type;

    public DeleteFilter(String id, String type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean filter(Object id) {
        return false;
    }

    @Override
    public boolean[] preFilter(Object[] id) {
        return new boolean[0];
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    static {
        Registry.addFilter(new Builder<ParameterizedFilter>() {
            private final Logger logger = LoggerFactory.getLogger(getClass());
            @Override
            public List<BuilderParam> getParams() {
                return Arrays.asList(
                        new BuilderParam("id", BuilderParam.Type.STRING, "", null),
                        new BuilderParam("type", BuilderParam.Type.STRING, "", null)
                );
            }

            @Override
            public ParameterizedFilter build(JsonNode params) {
                try {
                    String id = params.get("id").asText();
                    String type = params.get("type").asText();

                    return (context) ->{
                        try {
                            return new DeleteFilter(id,type);
                        }catch (Exception e){
                            return null;
                        }
                    } ;
                }catch (Exception e){
                    return null;
                }
            }

            @Override
            public String getName() {
                return null;
            }
        });
    }
}
