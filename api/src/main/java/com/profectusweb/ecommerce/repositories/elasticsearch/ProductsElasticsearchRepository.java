package com.profectusweb.ecommerce.repositories.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<ProductElasticsearchEntity> findAllBySkuLikeOrNameLikeOrDescriptionLike(
            String sku,
            String name,
            String description,
            Pageable pageable
    );
}
