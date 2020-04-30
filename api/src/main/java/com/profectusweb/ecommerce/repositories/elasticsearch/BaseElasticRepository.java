package com.profectusweb.ecommerce.repositories.elasticsearch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface BaseElasticRepository<T, J> extends CrudRepository<T, J> {

    void deleteByDatabaseId(BigInteger id);

    Optional<T> findOneByDatabaseId(BigInteger id);
}
