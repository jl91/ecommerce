package com.profectusweb.ecommerce.repositories.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticSearchEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface BaseElasticsearchRepository<T extends ElasticSearchEntity, J> extends ElasticsearchRepository<T, J> {

    void deleteByDatabaseId(BigInteger id);

    Optional<T> findOneByDatabaseId(BigInteger id);
}
