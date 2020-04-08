package com.profectusweb.ecommerce.configuration;

import com.profectusweb.ecommerce.entities.RoleEntity;
import com.profectusweb.ecommerce.entities.UserEntity;
import com.profectusweb.ecommerce.repositories.RolesRepository;
import com.profectusweb.ecommerce.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InitialDataConfiguration implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UsersRepository usuarioRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void onApplicationEvent(ContextRefreshedEvent e) {
        List<RoleEntity> roles = (List<RoleEntity>) rolesRepository.findByDeletedAtIsNull();

        if (!roles.isEmpty()) {
            return;
        }

        RoleEntity roleMasterEntity = new RoleEntity();
        RoleEntity roleCustomerEntity = new RoleEntity();

        roleMasterEntity.setName("ROLE_MASTER");
        roleCustomerEntity.setName("ROLER_CUSTOMER");

        roleMasterEntity = rolesRepository.save(roleMasterEntity);
        roleCustomerEntity = rolesRepository.save(roleCustomerEntity);

        UserEntity userMasterEntity = new UserEntity();
        UserEntity userCustomerEntity = new UserEntity();

        userMasterEntity.setName("master")
                .setUsername("master")
                .setPassword("master")
                .setRole(roleMasterEntity);

        userCustomerEntity.setName("customer")
                .setUsername("customer")
                .setPassword("customer")
                .setRole(roleCustomerEntity);

        usuarioRepository.save(userMasterEntity);
        usuarioRepository.save(userCustomerEntity);
    }

}
