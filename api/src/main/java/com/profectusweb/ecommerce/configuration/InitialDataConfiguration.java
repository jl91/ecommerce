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
    @Transactional()
    public void onApplicationEvent(ContextRefreshedEvent e) {
        List<RoleEntity> roles = (List<RoleEntity>) rolesRepository.findByDeletedAtIsNull();

        if (!roles.isEmpty()) {
            return;
        }

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("master");

        roleEntity = rolesRepository.save(roleEntity);

        UserEntity userEntity = new UserEntity();

        userEntity.setName("master")
                .setUsername("master")
                .setPassword(passwordEncoder.encode("master"))
                .setRole(roleEntity);

        usuarioRepository.save(userEntity);
    }

}
