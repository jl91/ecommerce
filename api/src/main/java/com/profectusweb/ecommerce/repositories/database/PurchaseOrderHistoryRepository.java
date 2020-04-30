package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.PurchaseOrderHistoryEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface PurchaseOrderHistoryRepository extends BaseRepository<PurchaseOrderHistoryEntity, BigInteger> {
    @Override
    Iterable<PurchaseOrderHistoryEntity> findByDeletedAtIsNull();

    @Override
    Optional<PurchaseOrderHistoryEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
