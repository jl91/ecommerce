package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.CartEntity;

import java.math.BigInteger;

public interface CartsRepository extends BaseRepository<CartEntity, BigInteger> {

    @Override
    Iterable<CartEntity> findByDeletedAtIsNull();
}
