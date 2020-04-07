package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.ProductInventoryEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface ProductInventoryRepository extends BaseRepository<ProductInventoryEntity, BigInteger> {
    @Override
    Iterable<ProductInventoryEntity> findByDeletedAtIsNull();

    @Override
    Optional<ProductInventoryEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
