package reco.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class State {
    private Map<String,Object> cache = null;
    @JsonIgnore
    private Set<String> saveKey;
    public State(Map<String,Object> param){
        cache = param;
        saveKey = new HashSet<>();
    }

    public Map<String,Object> getAllState() {return this.cache;}

    public void addSaveKey(String key) {saveKey.add(key);}

    @JsonIgnore
    public Object getValue(String key){return cache.get(key);}

    public void setValue(String key,Object value){cache.put(key,value);}

    public void setLimit(int limit) {cache.put("limit",limit);}

    public int getLimit(){return !cache.containsKey("limit")?10:(int) cache.get("limit");}

    public Map<String,String> getSaveMap(){
        Map<String,String> map = new HashMap<>();
        for (String key:saveKey){
            if(cache.containsKey(key) && cache.get(key) != null){
                map.put(key,cache.get(key).toString());
            }
        }
        return map;
    }

    public void setDebug(int debug) {cache.put("debug",debug);}

    public int getDebug() {return !cache.containsKey("debug")?0:(int)cache.get("debug");}
}
