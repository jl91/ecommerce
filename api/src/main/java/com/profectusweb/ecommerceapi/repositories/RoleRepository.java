package com.profectusweb.ecommerceapi.repositories;

import com.profectusweb.ecommerceapi.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface RoleRepository extends CrudRepository<Role, BigInteger> {

}
