package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.ProductInventoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ProductInventoryRepository extends CrudRepository<ProductInventoryEntity, BigInteger> {

}
