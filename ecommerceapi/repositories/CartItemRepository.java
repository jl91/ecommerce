package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.CartItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CartItemRepository extends CrudRepository<CartItemEntity, BigInteger> {

}
