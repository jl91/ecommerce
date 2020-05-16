package com.profectusweb.ecommerce.repositories.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface BaseElasticsearchRepository<T extends ElasticsearchEntity, J> extends ElasticsearchRepository<T, J> {

    void deleteById(BigInteger id);

    Optional<T> findOneById(BigInteger id);
}
