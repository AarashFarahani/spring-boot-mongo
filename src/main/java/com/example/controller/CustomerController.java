package com.example.controller;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/")
    public String getCustomer() {
        MongoCollection<Document> collection = this.mongoTemplate.getDb().getCollection("customer");

        String result = StreamSupport.stream(collection.find().spliterator(), false)
                .map(Document::toJson)
                .collect(Collectors.joining(", ", "[", "]"));

        return result;
    }

    @PostMapping(value = "/addCustomer", produces = { MediaType.APPLICATION_JSON_VALUE })
    public void addCustomer(@RequestBody String customer) {
        Document doc = Document.parse(customer);
        this.mongoTemplate.insert(doc, "customer");
    }
}
