package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.CartEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CartsRepository extends CrudRepository<CartEntity, BigInteger> {

}
