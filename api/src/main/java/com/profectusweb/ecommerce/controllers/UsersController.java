package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.UserEntity;
import com.profectusweb.ecommerce.repositories.UsersRepository;
import com.profectusweb.ecommerce.requests.UsersRequestBody;
import com.profectusweb.ecommerce.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController()
@RequestMapping("/users")
public class UsersController extends BaseController<UserEntity> {

    UsersService usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsersController(
            UsersRepository usersRepository,
            UsersService usersService
    ) {
        super("User",
                usersRepository
        );

        this.usersService = usersService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity create(
            @Valid @RequestBody UsersRequestBody incommingRequestBody
    ) {
        incommingRequestBody.password = passwordEncoder.encode(incommingRequestBody.password);
        return this.usersService
                .create(incommingRequestBody);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity update(
            @PathVariable(name = "id") BigInteger id,
            @Valid @RequestBody UsersRequestBody incommingRequestBody
    ) {
        incommingRequestBody.id = id;
        return this.usersService
                .update(incommingRequestBody);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(
            @PathVariable(name = "id") BigInteger id
    ) {
        this.usersService
                .remove(id);
    }

}
