package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.database.BaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigInteger;

public abstract class BaseController<T> {

    private BaseRepository<T, BigInteger> repository;
    private String entityName;

    BaseController(
            String entityName,
            BaseRepository<T, BigInteger> repository
    ) {
        this.entityName = entityName;
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<T> all() {
        return (Iterable<T>) repository.findByDeletedAtIsNull();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T byId(@PathVariable(name = "id") BigInteger id) throws ResourceNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName, id));
    }

}
