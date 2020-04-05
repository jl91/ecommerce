package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ProductsRepository extends CrudRepository<ProductEntity, BigInteger> {

}
