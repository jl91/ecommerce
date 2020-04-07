package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.CartItemEntity;

import java.math.BigInteger;

public interface CartItemRepository extends BaseRepository<CartItemEntity, BigInteger> {

    @Override
    Iterable<CartItemEntity> findByDeletedAtIsNull();
}
