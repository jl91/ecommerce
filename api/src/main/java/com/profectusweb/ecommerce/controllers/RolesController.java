package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.RoleEntity;
import com.profectusweb.ecommerce.exceptions.RoleNotFoundException;
import com.profectusweb.ecommerce.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController()
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RolesRepository rolesRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<RoleEntity> all() {
        return rolesRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleEntity byId(@PathVariable(name = "id") BigInteger id) throws RoleNotFoundException {
        return this.rolesRepository
                .findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }
}
