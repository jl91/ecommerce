package com.profectusweb.ecommerce.repositories;

import com.profectusweb.ecommerce.entities.RoleEntity;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface RolesRepository extends CrudRepository<RoleEntity, BigInteger> {

}
