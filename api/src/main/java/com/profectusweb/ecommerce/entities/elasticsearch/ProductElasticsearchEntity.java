package com.profectusweb.ecommerce.entities.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Document(
        indexName = "products",
        shards = 1,
        replicas = 0,
        refreshInterval = "-1",
        type = "product"
)
public class ProductElasticsearchEntity extends ElasticSearchEntity {

    private String sku;

    private String name;

    private String description;

    private Float value;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getSku() {
        return sku;
    }

    public ProductElasticsearchEntity setSku(String sku) {
        this.sku = sku;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductElasticsearchEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductElasticsearchEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Float getValue() {
        return value;
    }

    public ProductElasticsearchEntity setValue(Float value) {
        this.value = value;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public ProductElasticsearchEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ProductElasticsearchEntity setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
