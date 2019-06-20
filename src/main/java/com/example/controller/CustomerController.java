package com.example.controller;

import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String template = "Hello, %s!";

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        MongoCollection<Document> collection = this.mongoTemplate.getDb().getCollection("customer");
        DBCursor cursorDoc = collection.find();
        while (cursorDoc.hasNext()) {
            System.out.println(cursorDoc.next());
        }
    }

    @PostMapping(value = "/addCustomer", headers="Accept=application/json")
    public void addCustomer(@RequestBody String customer) {
        Document doc = Document.parse(customer);
        this.mongoTemplate.insert(doc, "customer");
    }
}
