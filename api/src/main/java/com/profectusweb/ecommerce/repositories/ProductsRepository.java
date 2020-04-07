package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.ProductEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductsRepository extends BaseRepository<ProductEntity, BigInteger> {

    @Override
    Iterable<ProductEntity> findByDeletedAtIsNull();

    @Override
    Optional<ProductEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
