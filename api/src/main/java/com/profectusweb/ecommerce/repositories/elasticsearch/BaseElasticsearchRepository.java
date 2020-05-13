package com.profectusweb.ecommerce.repositories.elasticsearch;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface BaseElasticsearchRepository<T, J> extends PagingAndSortingRepository<T, J> {

    void deleteByDatabaseId(BigInteger id);

    Optional<T> findOneByDatabaseId(BigInteger id);
}
