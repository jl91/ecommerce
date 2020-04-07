package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.ProductEntity;
import com.profectusweb.ecommerce.exceptions.ResourceNotFoundException;
import com.profectusweb.ecommerce.repositories.ProductsRepository;
import com.profectusweb.ecommerce.requests.ProductsRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("ProductsService")
public class ProductsService implements CustomServiceInterface<ProductEntity, ProductsRequestBody> {

    @Autowired
    ProductsRepository productsRepository;

    @Override
    public ProductEntity create(ProductsRequestBody data) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setSku(data.sku)
                .setName(data.name)
                .setValue(data.value)
                .setDescription(data.description)
        ;

        return this.productsRepository
                .save(productEntity);
    }

    @Override
    public ProductEntity update(ProductsRequestBody data) {
        ProductEntity productEntity = this.productsRepository
                .findByDeletedAtIsNullAndId(data.id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", data.id));

        productEntity.setSku(data.sku)
                .setName(data.name)
                .setValue(data.value)
                .setDescription(data.description)
        ;

        return this.productsRepository
                .save(productEntity);
    }

    @Override
    public boolean remove(BigInteger id) {
        ProductEntity productEntity = this.productsRepository
                .findByDeletedAtIsNullAndId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));

        productEntity.preRemove();

         this.productsRepository
                .save(productEntity);

         return true;
    }
}
