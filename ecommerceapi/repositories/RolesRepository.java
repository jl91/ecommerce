package com.profectusweb.ecommerceapi.repositories;


import com.profectusweb.ecommerceapi.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface RolesRepository extends CrudRepository<RoleEntity, BigInteger> {

}
