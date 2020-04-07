package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.PurchaseOrderHistoryEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface PurchaseOrderHistoryRepository extends BaseRepository<PurchaseOrderHistoryEntity, BigInteger> {
    @Override
    Iterable<PurchaseOrderHistoryEntity> findByDeletedAtIsNull();

    @Override
    Optional<PurchaseOrderHistoryEntity> findByIdAndCreatedAtIsNull(BigInteger id);
}
