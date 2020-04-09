package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.UserEntity;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends BaseRepository<UserEntity, BigInteger> {

    @Override
    Iterable<UserEntity> findByDeletedAtIsNull();

    @Override
    Optional<UserEntity> findByDeletedAtIsNullAndId(BigInteger id);

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    Iterable<UserEntity> findAllByUsernameAndDeletedAtIsNull(String username);

}
