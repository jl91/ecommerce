package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.UserEntity;

import java.math.BigInteger;

public interface UsersRepository extends BaseRepository<UserEntity, BigInteger> {

    @Override
    Iterable<UserEntity> findByDeletedAtIsNull();
}
