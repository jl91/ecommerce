package com.profectusweb.ecommerce.repositories.database;

import com.profectusweb.ecommerce.entities.database.DatabaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface BaseRepository<T, J> extends CrudRepository<T, J> {

    Iterable<? extends DatabaseEntity> findByDeletedAtIsNull();

    Optional<? extends DatabaseEntity> findByDeletedAtIsNullAndId(BigInteger id);


}
