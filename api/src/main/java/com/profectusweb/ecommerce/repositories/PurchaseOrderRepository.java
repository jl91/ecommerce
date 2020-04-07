package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.PurchaseOrderEntity;

import java.math.BigInteger;

public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrderEntity, BigInteger> {

    @Override
    Iterable<PurchaseOrderEntity> findByDeletedAtIsNull();
}
