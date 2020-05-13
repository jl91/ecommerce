package com.profectusweb.ecommerce.services.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;
import com.profectusweb.ecommerce.repositories.elasticsearch.ProductsElasticsearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("ProductsPageableService")
public class ProductsPageableService extends BasePageableService<ProductElasticsearchEntity, BigInteger> {

    @Autowired
    ProductsPageableService(ProductsElasticsearchRepository productsElasticsearchRepository) {
        super(productsElasticsearchRepository);
    }

}
