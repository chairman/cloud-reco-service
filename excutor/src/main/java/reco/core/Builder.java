package reco.core;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface Builder<T> {
    List<BuilderParam> getParams();

    T build(JsonNode params);

    String getName();

    default String getTextFromJson(JsonNode jsonNode,String key){
        if(jsonNode.get(key) == null){
            return "";
        }else {
            return jsonNode.get(key).asText();
        }
    }
}
