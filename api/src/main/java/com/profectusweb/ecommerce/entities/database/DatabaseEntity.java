package com.profectusweb.ecommerce.entities.database;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;

public interface DatabaseEntity {

    public ElasticsearchEntity toElasticEntity();

}
