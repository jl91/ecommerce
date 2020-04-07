package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.CartItemEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface CartItemRepository extends BaseRepository<CartItemEntity, BigInteger> {

    @Override
    Iterable<CartItemEntity> findByDeletedAtIsNull();

    @Override
    Optional<CartItemEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
