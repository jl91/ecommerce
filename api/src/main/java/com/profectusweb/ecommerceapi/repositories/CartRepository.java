package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CartRepository extends CrudRepository<CartItem, BigInteger> {

}
