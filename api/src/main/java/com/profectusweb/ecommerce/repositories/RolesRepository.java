package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.RoleEntity;

import java.math.BigInteger;
import java.util.Optional;

public interface RolesRepository extends BaseRepository<RoleEntity, BigInteger> {

    @Override
    Iterable<RoleEntity> findByDeletedAtIsNull();

    @Override
    Optional<RoleEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
