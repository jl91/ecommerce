package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.PurchaseOrderHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface PurchaseOrderHistoryRepository extends CrudRepository<PurchaseOrderHistoryEntity, BigInteger> {

}
