package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.ProductInventory;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface ProductInventoryRepository extends CrudRepository<ProductInventory, BigInteger> {

}
