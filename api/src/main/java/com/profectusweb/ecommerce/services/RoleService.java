package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.RoleEntity;
import com.profectusweb.ecommerce.repositories.RolesRepository;
import com.profectusweb.ecommerce.requests.RoleRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public boolean remove(RoleRequestBody data) {
        return false;
    }
}
