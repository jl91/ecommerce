package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.ProductInventoryEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductInventoryRepository extends BaseRepository<ProductInventoryEntity, BigInteger> {
    @Override
    Iterable<ProductInventoryEntity> findByDeletedAtIsNull();

    @Override
    Optional<ProductInventoryEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
