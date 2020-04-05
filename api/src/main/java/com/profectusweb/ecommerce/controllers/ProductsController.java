package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.ProductEntity;
import com.profectusweb.ecommerce.repositories.ProductsRepository;
import com.profectusweb.ecommerce.requests.ProductsRequestBody;
import com.profectusweb.ecommerce.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
}
