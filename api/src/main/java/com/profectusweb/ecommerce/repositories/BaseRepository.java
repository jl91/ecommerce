package com.profectusweb.ecommerce.repositories;

import org.springframework.data.repository.CrudRepository;

public interface BaseRepository<T, J> extends CrudRepository<T, J> {

    Iterable<T> findByDeletedAtIsNull();

}
