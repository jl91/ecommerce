package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.database.BaseRepository;
import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.response.PageableResponse;
import com.profectusweb.ecommerce.services.elasticsearch.BasePageableService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigInteger;

public abstract class BaseController<T, J extends ElasticsearchEntity> {

    private BaseRepository<T, BigInteger> repository;
    private BasePageableService<J, BigInteger> elasticRepository;

    private String entityName;

    BaseController(
            String entityName,
            BaseRepository<T, BigInteger> repository,
            BasePageableService<J, BigInteger> elasticRepository
    ) {
        this.entityName = entityName;
        this.repository = repository;
        this.elasticRepository = elasticRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageableResponse<J> all(
            @RequestParam(name = "fields", required = false)
                    String fields,
            @RequestParam(name = "filters", required = false)
                    String filters,
            @RequestParam(name = "sorts", required = false)
                    String sorts,
            @RequestParam(name = "limit", required = false)
                    Integer limit,
            @RequestParam(name = "page", required = false)
                    Integer page,
            @RequestParam(name = "search", required = false)
                    String search
    ) {

        PageableResponse<J> response = this.elasticRepository
                .findBy(
                        new ApiQueryParams(
                                fields,
                                filters,
                                sorts,
                                limit,
                                page,
                                search
                        )

                );

        response.setStatus(200);

        return response;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public T byId(@PathVariable(name = "id") BigInteger id) throws ResourceNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(entityName, id));
    }

}
