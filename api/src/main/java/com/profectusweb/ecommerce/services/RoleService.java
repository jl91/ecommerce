package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.RoleEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.RolesRepository;
import com.profectusweb.ecommerce.requests.RoleRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("RoleService")
public class RoleService implements CustomServiceInterface<RoleEntity, RoleRequestBody> {

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public RoleEntity create(RoleRequestBody data) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(data.name);
        return this.rolesRepository.save(roleEntity);
    }

    @Override
    public RoleEntity update(RoleRequestBody data) {
        RoleEntity roleEntity = this.rolesRepository
                .findById(data.id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", data.id));

        roleEntity.setName(data.name);
        return this.rolesRepository.save(roleEntity);
    }

    @Override
    public boolean remove(BigInteger id) {
        RoleEntity roleEntity = this.rolesRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));

        roleEntity.preRemove();
        this.rolesRepository.save(roleEntity);
        return true;
    }
}
