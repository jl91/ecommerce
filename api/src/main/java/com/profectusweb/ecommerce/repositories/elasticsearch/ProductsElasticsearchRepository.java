package com.profectusweb.ecommerce.repositories.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductsElasticsearchRepository
        extends BaseElasticsearchRepository<ProductElasticsearchEntity, BigInteger> {

    @Override
    void deleteById(BigInteger id);

    @Override
    Optional<ProductElasticsearchEntity> findOneById(BigInteger id);
}
