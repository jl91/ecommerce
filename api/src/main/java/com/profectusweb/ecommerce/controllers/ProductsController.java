package com.profectusweb.ecommerce.controllers;

import com.profectusweb.ecommerce.entities.database.ProductEntity;
import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;
import com.profectusweb.ecommerce.repositories.database.ProductsRepository;
import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.requests.ProductsRequestBody;
import com.profectusweb.ecommerce.response.PageableResponse;
import com.profectusweb.ecommerce.services.database.ProductsService;
import com.profectusweb.ecommerce.services.elasticsearch.ProductsPageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;

@RestController()
@RequestMapping("/products")
class ProductsController extends BaseController<ProductEntity, ProductElasticsearchEntity> {

    private ProductsService productsService;

    private ProductsPageableService productsPageableService;

    @Autowired
    ProductsController(
            ProductsRepository productsRepository,
            ProductsService productsService,
            ProductsPageableService productsPageableService
    ) {
        super(
                "Product",
                productsRepository,
                productsPageableService
        );
        this.productsService = productsService;
        this.productsPageableService = productsPageableService;
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
