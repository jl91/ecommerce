package com.profectusweb.ecommerceapi.repositories;


import com.profectusweb.ecommerceapi.entities.PurchaseOrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrderEntity, BigInteger> {

}
