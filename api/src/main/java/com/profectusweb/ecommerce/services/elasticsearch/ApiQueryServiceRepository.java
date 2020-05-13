package com.profectusweb.ecommerce.services.elasticsearch;

import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.response.PageableResponse;

public interface ApiQueryServiceRepository<T> {

    PageableResponse<T> findBy(ApiQueryParams params);

}
