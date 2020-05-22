package com.profectusweb.ecommerce.services.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ProductElasticsearchEntity;
import com.profectusweb.ecommerce.repositories.elasticsearch.ProductsElasticsearchRepository;
import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.response.PageableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service("ProductsPageableService")
public class ProductsPageableService extends BasePageableService<ProductElasticsearchEntity, BigInteger> {

    ProductsElasticsearchRepository productsElasticsearchRepository;

    @Autowired
    ProductsPageableService(ProductsElasticsearchRepository productsElasticsearchRepository) {
        super(productsElasticsearchRepository);
        this.productsElasticsearchRepository = productsElasticsearchRepository;
    }

    @Override
    public PageableResponse<ProductElasticsearchEntity> findBy(ApiQueryParams params) {

        if (params.getSearch().isEmpty()) {
            return super.findBy(params);
        }

        Pageable pageable = super.getPageable(params);

        if (!params.getSorts().isEmpty()) {
            pageable = super.configSort(pageable, params);
        }

        String value = params.getSearch().orElse("");

        Page<ProductElasticsearchEntity> page = this.productsElasticsearchRepository
                .findAllByIdLikeOrSkuLikeOrNameLikeOrDescriptionLike(
                        value,
                        value,
                        value,
                        value,
                        pageable
                );

        return super.buildResponse(page);
    }

}
