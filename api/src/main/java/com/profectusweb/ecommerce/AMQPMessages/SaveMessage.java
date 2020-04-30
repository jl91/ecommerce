package com.profectusweb.ecommerce.AMQPMessages;

import java.math.BigInteger;

public class SaveMessage {

    private BigInteger id;

    private String entity;

    public BigInteger getId() {
        return id;
    }

    public SaveMessage setId(BigInteger id) {
        this.id = id;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public SaveMessage setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    @Override
    public String toString() {
        return String.format("[ id = %d, entity = %s ]", id, entity);
    }
}
