package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.UserEntity;
import com.profectusweb.ecommerce.repositories.UsersRepository;
import com.profectusweb.ecommerce.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UsersController extends BaseController<UserEntity> {

    UsersService usersService;

    @Autowired
    public UsersController(
            UsersRepository usersRepository,
            UsersService usersService
    ){
        super("User",
                usersRepository
        );

        this.usersService = usersService;
    }

}
