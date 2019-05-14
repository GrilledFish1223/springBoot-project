package com.ping.mongo.dao;

import com.ping.mongo.entity.MongoTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;


/**
 * @author: zhangsp
 * @date: 2019/3/20 17:04
 * @copyright: @2019
 */
@Component
public class MongoTestDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(MongoTest mongoTest) {
        mongoTemplate.save(mongoTest);
    }

    public MongoTest find(String name) {
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, MongoTest.class);
    }

    public void updateTest(MongoTest test) {
        Query query = new Query(Criteria.where("id").is(test.getId()));
        Update update = new Update().set("age", test.getAge()).set("name", test.getName());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MongoTest.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }

    /**
     * 删除对象
     *
     * @param id
     */
    public void deleteTestById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, MongoTest.class);
    }


}
