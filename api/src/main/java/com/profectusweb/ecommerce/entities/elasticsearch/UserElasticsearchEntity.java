package com.profectusweb.ecommerce.entities.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(
        indexName = "users",
        shards = 1,
        replicas = 0,
        refreshInterval = "-1",
        type = "user"
)
public class UserElasticsearchEntity extends ElasticSearchEntity {

    private String username;

    private String password;

    private String name;

    public String getUsername() {
        return username;
    }

    public UserElasticsearchEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserElasticsearchEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserElasticsearchEntity setName(String name) {
        this.name = name;
        return this;
    }
}
