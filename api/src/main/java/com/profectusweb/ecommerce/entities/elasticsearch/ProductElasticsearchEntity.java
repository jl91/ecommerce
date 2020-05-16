package com.profectusweb.ecommerce.entities.elasticsearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.Date;

@Document(
        indexName = "products",
        shards = 1,
        replicas = 0,
        refreshInterval = "-1"
)
public class ProductElasticsearchEntity extends ElasticsearchEntity {

    @Field(type = FieldType.Text, store = true)
    private String sku;

    @Field(type = FieldType.Text, store = true)
    private String name;

    @Field(type = FieldType.Text, store = true)
    private String description;

    @Field(type = FieldType.Float, store = true)
    private Float value;

    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
    private LocalDateTime createdAt;

    @Field(type = FieldType.Date, store = true, format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm'Z'")
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
