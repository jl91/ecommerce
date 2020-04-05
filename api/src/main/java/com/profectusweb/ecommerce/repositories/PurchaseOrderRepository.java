package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.PurchaseOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrderEntity, BigInteger> {

}
