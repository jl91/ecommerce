package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UsersRepository extends CrudRepository<UserEntity, BigInteger> {

}
