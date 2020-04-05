package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.PurchaseOrderHistory;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface PurchaseOrderHistoryRepository extends CrudRepository<PurchaseOrderHistory, BigInteger> {

}
