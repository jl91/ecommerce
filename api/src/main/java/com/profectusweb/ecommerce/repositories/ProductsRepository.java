package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.ProductEntity;

import java.math.BigInteger;

public interface ProductsRepository extends BaseRepository<ProductEntity, BigInteger> {

    @Override
    Iterable<ProductEntity> findByDeletedAtIsNull();
}
