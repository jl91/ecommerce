package com.profectusweb.ecommerce.entities.database;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticSearchEntity;

public interface DatabaseEntity {

    public ElasticSearchEntity toElasticEntity();

}
