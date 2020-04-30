package com.profectusweb.ecommerce.entities.elasticsearch;

import javax.persistence.Id;
import java.math.BigInteger;

public class ElasticSearchEntity {

    @Id
    private BigInteger id;

    private BigInteger databaseId;

    public BigInteger getId() {
        return id;
    }

    public ElasticSearchEntity setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public BigInteger getDatabaseId() {
        return databaseId;
    }

    public ElasticSearchEntity setDatabaseId(BigInteger databaseId) {
        this.databaseId = databaseId;
        return this;
    }
}
