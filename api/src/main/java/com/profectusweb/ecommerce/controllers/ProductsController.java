package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.database.ProductEntity;
import com.profectusweb.ecommerce.repositories.database.ProductsRepository;
import com.profectusweb.ecommerce.requests.ProductsRequestBody;
import com.profectusweb.ecommerce.services.database.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController()
@RequestMapping("/products")
class ProductsController extends BaseController<ProductEntity> {

    private ProductsService productsService;

    @Autowired
    ProductsController(
            ProductsRepository productsRepository,
            ProductsService productsService
    ) {
        super(
                "Products",
                productsRepository
        );
        this.productsService = productsService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity create(
            @Valid @RequestBody ProductsRequestBody incommingRequestBody
    ) {
        return this.productsService
                .create(incommingRequestBody);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity create(
            @PathVariable(name = "id") BigInteger id,
            @Valid @RequestBody ProductsRequestBody incommingRequestBody
    ) {
        incommingRequestBody.id = id;
        return this.productsService
                .update(incommingRequestBody);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(
            @PathVariable(name = "id") BigInteger id
    ) {
        this.productsService
                .remove(id);
    }
}
