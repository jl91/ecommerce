package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ProductRepository extends CrudRepository<Product, BigInteger> {

}
