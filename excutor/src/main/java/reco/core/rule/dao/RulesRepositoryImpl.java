package reco.core.rule.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import reco.core.JsonUtils;
import reco.core.rule.model.RuleConfig;
import reco.core.rule.model.RuleInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RulesRepositoryImpl implements RulesRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<RuleConfig> list() {
        BasicDBObject where = new BasicDBObject();
        BasicDBObject sort = new BasicDBObject("order",1).append("ruleId",1);
        FindIterable<Document> fi = mongoTemplate.getCollection(RuleInfo.T_BASE_RULES.getTableName()).find(where).sort(sort);
        List<RuleConfig> list = new ArrayList<>();
        for (Document doc:fi){
            list.add(doc2DO(doc,RuleConfig.class));
        }
        return list;
    }

    private <T> T doc2DO(Document doc,Class<T> cls){
        if(doc==null) return null;
        JSONObject json = new JSONObject();
        doc.forEach(json::put);
        return JSON.toJavaObject(json,cls);
    }

    @Override
    public List<RuleConfig> listPre() {
        BasicDBObject where = new BasicDBObject();
        BasicDBObject sort = new BasicDBObject("order",1).append("ruleId",1);
        FindIterable<RuleConfig> fi = mongoTemplate.getCollection(RuleInfo.T_PRE_BASE_RULES.getTableName()).find(where,RuleConfig.class).sort(sort);
        List<RuleConfig> list = new ArrayList<>();
        for (RuleConfig doc:fi){
            list.add(doc);
        }
        return list;
    }

    @Override
    public void addRule(RuleConfig ruleConfig) {
        Map<String,Object> map = JsonUtils.getNotNullFieldToMap(ruleConfig);
        Document doc = new Document();
        for (Map.Entry<String,Object> entry:map.entrySet()){
            doc.put(entry.getKey(),entry.getValue());
        }
        mongoTemplate.getCollection(RuleInfo.T_PRE_BASE_RULES.getTableName()).insertOne(doc);
    }

    @Override
    public void updateRule(RuleConfig ruleConfig) {
        BasicDBObject where = new BasicDBObject();
        where.put("ruleId",ruleConfig.getRuleId());
        Map<String,Object> map = JsonUtils.getNotNullFieldToMap(ruleConfig);
        Document doc = new Document();
        for (Map.Entry<String,Object> entry:map.entrySet()){
            doc.put(entry.getKey(),entry.getValue());
        }
        mongoTemplate.getCollection(RuleInfo.T_PRE_BASE_RULES.getTableName()).updateOne(where,doc);
    }

    @Override
    public void deleteRule(int ruleId) {
        BasicDBObject where = new BasicDBObject();
        where.put("ruleId",ruleId);
        mongoTemplate.getCollection(RuleInfo.T_PRE_BASE_RULES.getTableName()).deleteOne(where);
    }
}

