package com.profectusweb.ecommerce.entities.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

public abstract class ElasticsearchEntity {

    @Id
    @Field(type = FieldType.Integer, store = true)
    private int id;

    @Field(type = FieldType.Integer, store = true)
    private int databaseId;

    public int getId() {
        return id;
    }

    public ElasticsearchEntity setId(int id) {
        this.id = id;
        return this;
    }

    public int getDatabaseId() {
        return databaseId;
    }

    public ElasticsearchEntity setDatabaseId(int databaseId) {
        this.databaseId = databaseId;
        return this;
    }
}
