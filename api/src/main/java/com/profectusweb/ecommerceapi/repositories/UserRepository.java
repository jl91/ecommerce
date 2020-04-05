package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserRepository extends CrudRepository<User, BigInteger> {

}
