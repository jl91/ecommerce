package com.profectusweb.ecommerce.services.database;

import com.profectusweb.ecommerce.entities.database.RoleEntity;
import com.profectusweb.ecommerce.entities.database.UserEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.database.UsersRepository;
import com.profectusweb.ecommerce.requests.UsersRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("UsersService")
public class UsersService implements CustomServiceInterface<UserEntity, UsersRequestBody> {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesService rolesService;

    @Override
    public UserEntity create(UsersRequestBody data) {
        UserEntity userEntity = new UserEntity();

        RoleEntity roleEntity = rolesService.getRoleById(data.roleId);

        userEntity.setName(data.name)
                .setUsername(data.username.toLowerCase())
                .setPassword(data.password)
                .setRole(roleEntity)
        ;

        return this.usersRepository
                .save(userEntity);
    }

    @Override
    public UserEntity update(UsersRequestBody data) {

        UserEntity userEntity = this.usersRepository
                .findByDeletedAtIsNullAndId(data.id)
                .orElseThrow(() -> new ResourceNotFoundException("User", data.id));

        RoleEntity roleEntity = rolesService.getRoleById(data.roleId);


        userEntity.setName(data.name)
                .setUsername(data.username)
                .setPassword(
                        new BCryptPasswordEncoder(10)
                                .encode(data.password)
                )
                .setRole(roleEntity)
        ;

        return this.usersRepository
                .save(userEntity);
    }

    @Override
    public boolean remove(BigInteger id) {
        UserEntity userEntity = this.usersRepository
                .findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", id));

        userEntity.preRemove();

        this.usersRepository
                .save(userEntity);

        return true;
    }
}
