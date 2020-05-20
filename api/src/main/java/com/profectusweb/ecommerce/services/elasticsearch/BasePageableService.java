package com.profectusweb.ecommerce.services.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;
import com.profectusweb.ecommerce.repositories.elasticsearch.BaseElasticsearchRepository;
import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.response.PageableResponse;
import com.profectusweb.ecommerce.response.PaginationMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class BasePageableService<T extends ElasticsearchEntity, J> implements ApiQueryServiceRepository<T> {

    private BaseElasticsearchRepository<T, J> repository;

    BasePageableService(BaseElasticsearchRepository repository) {
        this.repository = repository;
    }

    protected final Integer INITIAL_PAGE = 1;

    protected final Integer INITIAL_PAGE_SIZE = 10;

    @Override
    public PageableResponse<T> findBy(ApiQueryParams params) {

        if (!params.getSorts().isEmpty()) {

        }

        if (!params.getFilters().isEmpty()) {

        }

        if (!params.getFields().isEmpty()) {

        }

        final Pageable pageable = this.getPageable(params);

        final Page<T> page = this.repository.findAll(pageable);

        return this.buildResponse(page);
    }

    protected Pageable getPageable(ApiQueryParams params) {
        Integer currentPageNumber = params.getPage().orElse(INITIAL_PAGE);
        currentPageNumber = currentPageNumber <= 1 ? 1 : currentPageNumber;
        final Integer pageSize = params.getLimit().orElse(INITIAL_PAGE_SIZE);
        return PageRequest.of(
                currentPageNumber - 1,
                pageSize
        );
    }

    protected PageableResponse buildResponse(Page<T> page) {
        return new PageableResponse<T>()
                .setData(page.getContent())
                .setPaginationMetadata(
                        new PaginationMetadata(
                                page.getNumber() + 1,
                                page.getSize(),
                                (int) page.getTotalElements(),
                                page.getTotalPages()
                        )
                );
    }
}
