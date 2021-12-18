package reco.core.rule.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class IdsRepositoryImpl implements IdsRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public int getNext(String name) {
        try {
            BasicDBObject where = new BasicDBObject();
            where.put("name",name);
            BasicDBObject inc = new BasicDBObject("$inc",new BasicDBObject("current",1));
            Document document = mongoTemplate.getCollection(IdInfo.T_BASE_IDS.getTableName()).findOneAndUpdate(where,inc);
            if(document==null){
                Document doc = new Document();
                doc.put("name",name);
                doc.put("current",1);
                mongoTemplate.getCollection(IdInfo.T_BASE_IDS.getTableName()).insertOne(doc);
                return 1;
            }
            return document.getInteger("current");
        }catch (Exception e){

        }
        return 1;
    }

    public enum IdInfo{
        T_BASE_IDS("t_base_ids",Id.class);
        private String tableName;
        private Class entityClass;
        IdInfo(String tableName,Class entityClass) {
            this.tableName = tableName;
            this.entityClass = entityClass;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public Class getEntityClass() {
            return entityClass;
        }

        public void setEntityClass(Class entityClass) {
            this.entityClass = entityClass;
        }
    }

    private class Id{
        private int current;

        public int getCurrent() {
            return current;
        }

        public void setCurrent(int current) {
            this.current = current;
        }
    }
    /**
     * 根据用户名查询对象
     * @param userName
     * @return
     */
//    @Override
//    public User findUserByUserName(String userName) {
//        Query query=new Query(Criteria.where("userName").is(userName));
//        User user =  mongoTemplate.findOne(query , User.class);
//        return user;
//    }
//
//    /**
//     * 更新对象
//     * @param user
//     */
//    @Override
//    public long updateUser(User user) {
//        Query query=new Query(Criteria.where("id").is(user.getId()));
//        Update update= new Update().set("userName", user.getUserName()).set("passWord", user.getPassWord());
//        //更新查询返回结果集的第一条
//        UpdateResult result =mongoTemplate.updateFirst(query,update,User.class);
//        //更新查询返回结果集的所有
//        // mongoTemplate.updateMulti(query,update,UserEntity.class);
//        if(result!=null)
//            return result.getMatchedCount();
//        else
//            return 0;
//    }
//
//    /**
//     * 删除对象
//     * @param id
//     */
//    @Override
//    public void deleteUserById(Long id) {
//        Query query = new Query(Criteria.where("id").is(id));
//        mongoTemplate.remove(query,User.class);
//    }
}

