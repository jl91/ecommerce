package com.profectusweb.ecommerce.services.elasticsearch;

import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.response.PageableResponse;
import com.profectusweb.ecommerce.response.PaginationMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public abstract class BasePageableService<T, J> implements ApiQueryServiceRepository<T> {

    private PagingAndSortingRepository<T, J> repository;

    BasePageableService(PagingAndSortingRepository repository) {
        this.repository = repository;
    }

    private final Integer INITIAL_PAGE = 1;

    private final Integer INITIAL_PAGE_SIZE = 10;

    @Override
    public PageableResponse<T> findBy(ApiQueryParams params) {

        final Integer currentPageNumber = params.getPage().orElse(INITIAL_PAGE);

        final Integer pageSize = params.getLimit().orElse(INITIAL_PAGE_SIZE);

        if (!params.getSorts().isEmpty()) {

        }

        if (!params.getFilters().isEmpty()) {

        }

        if (!params.getFields().isEmpty()) {

        }

        Pageable pageable = PageRequest.of(currentPageNumber - 1, pageSize);

        Page<T> page = this.repository.findAll(pageable);

        return this.buildResponse(page);
    }

    private PageableResponse buildResponse(Page<T> page) {
        System.out.println(page);
        return new PageableResponse<T>()
                .setData(page.getContent())
                .setPaginationMetadata(
                        new PaginationMetadata(
                                page.getNumber(),
                                page.getSize(),
                                (int) page.getTotalElements()
                        )
                );
    }
}
