package com.profectusweb.ecommerce.services.database;

import com.profectusweb.ecommerce.configuration.EcommerceUserDetails;
import com.profectusweb.ecommerce.entities.database.UserEntity;
import com.profectusweb.ecommerce.repositories.database.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EcommerceUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository
                .findByUsernameAndDeletedAtIsNull(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new EcommerceUserDetails(userEntity);
    }



}
