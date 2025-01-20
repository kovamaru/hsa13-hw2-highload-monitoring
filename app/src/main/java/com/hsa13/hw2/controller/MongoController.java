package com.hsa13.hw2.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

@Controller("/api/mongo")
public class MongoController {

  @Inject
  MongoClient mongoClient;

  @Get("/add")
  public String addToMongo() {
    MongoCollection<Document> collection = mongoClient.getDatabase("testdb").getCollection("testcollection");
    Document doc = new Document("name", "TestName")
        .append("value", Math.random());
    collection.insertOne(doc);
    return "Inserted to MongoDB: " + doc.toJson();
  }

  @Get("/get")
  public List<String> getFromMongo() {
    MongoCollection<Document> collection = mongoClient.getDatabase("testdb").getCollection("testcollection");
    List<String> results = new ArrayList<>();
    for (Document doc : collection.find()) {
      results.add(doc.toJson());
    }
    return results;
  }
}
