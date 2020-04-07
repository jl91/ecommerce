package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.RoleEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.RolesRepository;
import com.profectusweb.ecommerce.requests.RolesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("RoleService")
public class RolesService implements CustomServiceInterface<RoleEntity, RolesRequestBody> {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public RoleEntity create(RolesRequestBody data) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(data.name);
        return this.rolesRepository.save(roleEntity);
    }

    @Override
    public RoleEntity update(RolesRequestBody data) {
        RoleEntity roleEntity = this.rolesRepository
                .findByIdAndCreatedAtIsNull(data.id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", data.id));

        roleEntity.setName(data.name);
        return this.rolesRepository.save(roleEntity);
    }

    @Override
    public boolean remove(BigInteger id) {
        RoleEntity roleEntity = this.rolesRepository
                .findByIdAndCreatedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));

        roleEntity.preRemove();
        this.rolesRepository.save(roleEntity);
        return true;
    }
}
