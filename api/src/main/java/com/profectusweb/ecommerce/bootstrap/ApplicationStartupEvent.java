package com.profectusweb.ecommerce.bootstrap;

import com.profectusweb.ecommerce.entities.database.RoleEntity;
import com.profectusweb.ecommerce.entities.database.UserEntity;
import com.profectusweb.ecommerce.repositories.database.ProductsRepository;
import com.profectusweb.ecommerce.repositories.database.RolesRepository;
import com.profectusweb.ecommerce.repositories.database.UsersRepository;
import com.profectusweb.ecommerce.repositories.elasticsearch.ProductsElasticsearchRepository;
import com.profectusweb.ecommerce.repositories.elasticsearch.UsersElasticsearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ApplicationStartupEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    PasswordEncoder passwordEncoder;

    // Users
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UsersElasticsearchRepository usersElasticsearchRepository;

    // Products
    @Autowired
    ProductsRepository productsRepository;

    @Autowired
    ProductsElasticsearchRepository productsElasticsearchRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        initialRoles();
        loadElasticData();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void initialRoles() {
        List<RoleEntity> roles = (List<RoleEntity>) rolesRepository.findByDeletedAtIsNull();

        if (!roles.isEmpty()) {
            return;
        }

        loadMasterRole();
        loadCustomerRole();
    }

    private void loadMasterRole() {
        RoleEntity roleMasterEntity = new RoleEntity();
        roleMasterEntity.setName("ROLE_MASTER");
        roleMasterEntity = rolesRepository.save(roleMasterEntity);
        UserEntity userMasterEntity = new UserEntity();
        userMasterEntity.setName("master")
                .setUsername("master")
                .setPassword("master")
                .setRole(roleMasterEntity);
        usersRepository.save(userMasterEntity);
    }

    private void loadCustomerRole() {
        RoleEntity roleCustomerEntity = new RoleEntity();
        roleCustomerEntity.setName("ROLE_CUSTOMER");
        roleCustomerEntity = rolesRepository.save(roleCustomerEntity);
        UserEntity userCustomerEntity = new UserEntity();
        userCustomerEntity.setName("customer")
                .setUsername("customer")
                .setPassword("customer")
                .setRole(roleCustomerEntity);
        usersRepository.save(userCustomerEntity);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void loadElasticData() {
        this.syncProducts();
        this.syncUser();
    }

    private void syncProducts() {
        productsElasticsearchRepository.deleteAll();
        productsRepository.findByDeletedAtIsNull()
                .iterator()
                .forEachRemaining(productEntity -> {
                    productsElasticsearchRepository.save(productEntity.toElasticEntity());
                });

    }

    private void syncUser(){
        usersElasticsearchRepository.deleteAll();
        usersRepository.findByDeletedAtIsNull()
                .iterator()
                .forEachRemaining(userEntity -> {
                    usersElasticsearchRepository.save(userEntity.toElasticEntity());
                });
    }

}
