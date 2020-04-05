package com.profectusweb.ecommerce.services;

import com.profectusweb.ecommerce.entities.ProductEntity;
import com.profectusweb.ecommerce.requests.ProductsRequestBody;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("ProductsService")
public class ProductsService implements CustomServiceInterface<ProductEntity, ProductsRequestBody> {

    @Override
    public ProductEntity create(ProductsRequestBody data) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setSku(data.sku)
                .setName(data.name)
                .setValue(data.value)
                .setDescription(data.description)
        ;

        return null;
    }

    @Override
    public ProductEntity update(ProductsRequestBody data) {
        return null;
    }

    @Override
    public boolean remove(BigInteger id) {
        return false;
    }
}
