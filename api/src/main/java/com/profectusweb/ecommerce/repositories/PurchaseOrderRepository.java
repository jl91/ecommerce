package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.PurchaseOrderEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrderEntity, BigInteger> {

    @Override
    Iterable<PurchaseOrderEntity> findByDeletedAtIsNull();

    @Override
    Optional<PurchaseOrderEntity> findByIdAndCreatedAtIsNull(BigInteger id);
}
