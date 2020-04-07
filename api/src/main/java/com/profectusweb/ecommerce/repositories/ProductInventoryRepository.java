package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.ProductInventoryEntity;

import java.math.BigInteger;

public interface ProductInventoryRepository extends BaseRepository<ProductInventoryEntity, BigInteger> {
    @Override
    Iterable<ProductInventoryEntity> findByDeletedAtIsNull();
}
