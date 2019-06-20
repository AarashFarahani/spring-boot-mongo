package com.example.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public String getCustomer(Document document) {
        this.mongoTemplate.insert(document);
    }
}
