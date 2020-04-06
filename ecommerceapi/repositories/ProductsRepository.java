package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ProductsRepository extends CrudRepository<ProductEntity, BigInteger> {

}