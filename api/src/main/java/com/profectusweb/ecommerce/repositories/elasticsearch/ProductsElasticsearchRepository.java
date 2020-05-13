package com.profectusweb.ecommerce.repositories.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductsElasticsearchRepository
        extends BaseElasticsearchRepository<ProductElasticsearchEntity, BigInteger> {

    @Override
    void deleteByDatabaseId(BigInteger id);

    @Override
    Optional<ProductElasticsearchEntity> findOneByDatabaseId(BigInteger id);
}
