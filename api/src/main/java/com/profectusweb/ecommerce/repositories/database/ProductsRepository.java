package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.ProductEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface ProductsRepository extends BaseRepository<ProductEntity, BigInteger> {

    @Override
    Iterable<ProductEntity> findByDeletedAtIsNull();

    @Override
    Optional<ProductEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
