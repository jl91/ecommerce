package com.profectusweb.ecommerce.repositories;


import com.profectusweb.ecommerce.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UsersRepository extends CrudRepository<UserEntity, BigInteger> {

}
