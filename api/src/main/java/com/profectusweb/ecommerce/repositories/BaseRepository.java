package com.profectusweb.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface BaseRepository<T, J> extends CrudRepository<T, J> {

    Iterable<T> findByDeletedAtIsNull();

    Optional<T> findByDeletedAtIsNullAndId(BigInteger id);


}
