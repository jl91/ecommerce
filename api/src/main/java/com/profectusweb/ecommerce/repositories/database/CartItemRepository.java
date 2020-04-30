package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.CartItemEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CartItemRepository extends BaseRepository<CartItemEntity, BigInteger> {

    @Override
    Iterable<CartItemEntity> findByDeletedAtIsNull();

    @Override
    Optional<CartItemEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
