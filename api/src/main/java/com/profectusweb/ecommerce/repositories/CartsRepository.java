package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.CartEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CartsRepository extends CrudRepository<CartEntity, BigInteger> {

}
