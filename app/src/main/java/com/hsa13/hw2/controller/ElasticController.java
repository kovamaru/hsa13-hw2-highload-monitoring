package com.hsa13.hw2.controller;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.elasticsearch.core.HealthReportResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.GetIndexResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.serde.annotation.SerdeImport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SerdeImport(HealthReportResponse.class)
@SerdeImport(GetIndexResponse.class)
@Controller("/api/elastic")
public class ElasticController {

  private final ElasticsearchClient client;

  public ElasticController(ElasticsearchClient client) {
    this.client = client;
  }

  @Get("/health")
  public Map<String, Object> elasticHealth() {
    try {
      HealthResponse response = client.cluster().health();
      Map<String, Object> result = new HashMap<>();
      result.put("status", response.status());
      result.put("cluster_name", response.clusterName());
      return result;
    } catch (IOException e) {
      return Map.of("error", e.getMessage());
    }
  }


  @Post("/index-random")
  public String indexRandomDocument(@QueryValue String index) {
    try {
      Map<String, Object> document = new HashMap<>();
      document.put("id", UUID.randomUUID().toString());
      document.put("timestamp", System.currentTimeMillis());
      document.put("value", Math.random() * 100);

      IndexResponse response = client.index(i -> i
          .index(index)
          .document(document)
      );

      return "Document indexed with ID: " + response.id();
    } catch (IOException e) {
      return "Error indexing document: " + e.getMessage();
    }
  }

}
