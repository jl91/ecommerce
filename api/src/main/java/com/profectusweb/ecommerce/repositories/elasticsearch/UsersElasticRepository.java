package com.profectusweb.ecommerce.repositories.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.UserElasticsearchEntity;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface UsersElasticRepository extends BaseElasticRepository<UserElasticsearchEntity, BigInteger> {

    @Override
    Optional<UserElasticsearchEntity> findOneByDatabaseId(BigInteger id);

    @Override
    void deleteByDatabaseId(BigInteger id);
}
