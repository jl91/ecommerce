package com.profectusweb.ecommerce.services.database;

import com.profectusweb.ecommerce.entities.database.RoleEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.database.RolesRepository;
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
                .findByDeletedAtIsNullAndId(data.id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", data.id));

        roleEntity.setName(data.name);
        return this.rolesRepository.save(roleEntity);
    }

    @Override
    public boolean remove(BigInteger id) {
        RoleEntity roleEntity = this.rolesRepository
                .findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));

        roleEntity.preRemove();
        this.rolesRepository.save(roleEntity);
        return true;
    }

    protected RoleEntity getRoleById(BigInteger id) {
        return this.rolesRepository
                .findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", id));
    }
}
