package com.profectusweb.ecommerceapi.controllers;

import com.profectusweb.ecommerceapi.entities.RoleEntity;
import com.profectusweb.ecommerceapi.exceptions.RoleNotFoundException;
import com.profectusweb.ecommerceapi.repositories.RolesRepository;
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
