package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.UserEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UsersRepository extends BaseRepository<UserEntity, BigInteger> {

    @Override
    Iterable<UserEntity> findByDeletedAtIsNull();

    @Override
    Optional<UserEntity> findByDeletedAtIsNullAndId(BigInteger id);

    Optional<UserEntity> findByUsernameAndDeletedAtIsNull(String username);

    Iterable<UserEntity> findAllByUsernameAndDeletedAtIsNull(String username);

}
