package com.profectusweb.ecommerce.entities.elasticsearch;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(
        indexName = "users",
        shards = 1,
        replicas = 0,
        refreshInterval = "-1",
        type = "user"
)
public class UserElasticsearchEntity extends ElasticsearchEntity {

    private String username;

    private String password;

    private String name;

    private String role;

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

    public String getRole() {
        return role;
    }

    public UserElasticsearchEntity setRole(String role) {
        this.role = role;
        return this;
    }
}
