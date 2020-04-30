package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.RoleEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface RolesRepository extends BaseRepository<RoleEntity, BigInteger> {

    @Override
    Iterable<RoleEntity> findByDeletedAtIsNull();

    @Override
    Optional<RoleEntity> findByDeletedAtIsNullAndId(BigInteger id);
}
