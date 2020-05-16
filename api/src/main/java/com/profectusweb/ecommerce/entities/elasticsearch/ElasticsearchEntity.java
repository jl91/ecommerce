package com.profectusweb.ecommerce.entities.elasticsearch;

import javax.persistence.Id;
import java.math.BigInteger;

public abstract class ElasticsearchEntity {

    @Id
    private BigInteger id;

    private BigInteger databaseId;

    public BigInteger getId() {
        return id;
    }

    public ElasticsearchEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getDatabaseId() {
        return databaseId;
    }

    public ElasticsearchEntity setDatabaseId(BigInteger databaseId) {
        this.databaseId = databaseId;
        return this;
    }
}
