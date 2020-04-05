package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.exceptions.RecordNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigInteger;

public abstract class BaseController<T> {

    private CrudRepository<T, BigInteger> repository;
    private String entityName;

    BaseController(
            String entityName,
            CrudRepository<T, BigInteger> repository
    ) {
        this.entityName = entityName;
        this.repository = repository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<T> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T byId(@PathVariable(name = "id") BigInteger id) throws RecordNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new RecordNotFoundException(entityName, id));
    }

}
