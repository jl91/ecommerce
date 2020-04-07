package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.RoleEntity;

import java.math.BigInteger;

public interface RolesRepository extends BaseRepository<RoleEntity, BigInteger> {

    @Override
    Iterable<RoleEntity> findByDeletedAtIsNull();
}
