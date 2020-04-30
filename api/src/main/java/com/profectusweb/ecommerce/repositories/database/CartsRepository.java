package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.CartEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface CartsRepository extends BaseRepository<CartEntity, BigInteger> {

    @Override
    Iterable<CartEntity> findByDeletedAtIsNull();

    @Override
    Optional<CartEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
