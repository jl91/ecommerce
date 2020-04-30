package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.PurchaseOrderEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrderEntity, BigInteger> {

    @Override
    Iterable<PurchaseOrderEntity> findByDeletedAtIsNull();

    @Override
    Optional<PurchaseOrderEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
