package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.CartItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface CartItemRepository extends CrudRepository<CartItemEntity, BigInteger> {

}
