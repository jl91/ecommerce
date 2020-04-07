package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.PurchaseOrderHistoryEntity;

import java.math.BigInteger;

public interface PurchaseOrderHistoryRepository extends BaseRepository<PurchaseOrderHistoryEntity, BigInteger> {
    @Override
    Iterable<PurchaseOrderHistoryEntity> findByDeletedAtIsNull();
}
