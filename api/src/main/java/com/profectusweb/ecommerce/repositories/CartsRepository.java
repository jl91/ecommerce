package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.CartEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface CartsRepository extends BaseRepository<CartEntity, BigInteger> {

    @Override
    Iterable<CartEntity> findByDeletedAtIsNull();

    @Override
    Optional<CartEntity> findByIdAndCreatedAtIsNull(BigInteger id);
}
