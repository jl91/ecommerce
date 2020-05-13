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

        Integer currentPageNumber = INITIAL_PAGE;
        Integer pageSize = INITIAL_PAGE_SIZE;

        if (params.getPage() != currentPageNumber) {
            currentPageNumber = params.getPage();
        }

        if (params.getLimit() != pageSize) {
            pageSize = params.getLimit();
        }

        if (!params.getSorts().isEmpty()) {

        }

        if (!params.getFilters().isEmpty()) {

        }

        if (!params.getFields().isEmpty()) {

        }

        Pageable pageable = PageRequest.of(currentPageNumber, pageSize);

        Page<T> page = this.repository.findAll(pageable);

        return this.buildResponse(page);
    }

    private PageableResponse buildResponse(Page<T> page) {

        PageableResponse pageableResponse = new PageableResponse<T>();
        pageableResponse.setData(page.getContent());

        pageableResponse.setPaginationMetadata(
                new PaginationMetadata(
                        page.getNumber(),
                        page.getSize(),
                        (int) page.getTotalElements()
                )
        );

        return pageableResponse;
    }
}
