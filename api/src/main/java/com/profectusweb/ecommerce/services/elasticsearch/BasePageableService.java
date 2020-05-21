package com.profectusweb.ecommerce.services.elasticsearch;

import com.profectusweb.ecommerce.entities.elasticsearch.ElasticsearchEntity;
import com.profectusweb.ecommerce.repositories.elasticsearch.BaseElasticsearchRepository;
import com.profectusweb.ecommerce.requests.ApiQueryParams;
import com.profectusweb.ecommerce.requests.SortItemRequestBody;
import com.profectusweb.ecommerce.response.PageableResponse;
import com.profectusweb.ecommerce.response.PaginationMetadata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

public abstract class BasePageableService<T extends ElasticsearchEntity, J> implements ApiQueryServiceRepository<T> {

    private BaseElasticsearchRepository<T, J> repository;

    BasePageableService(BaseElasticsearchRepository repository) {
        this.repository = repository;
    }

    protected final Integer INITIAL_PAGE = 1;

    protected final Integer INITIAL_PAGE_SIZE = 10;

    @Override
    public PageableResponse<T> findBy(ApiQueryParams params) {

        Pageable pageable = this.getPageable(params);

        if (!params.getSorts().isEmpty()) {
            pageable = this.configSort(pageable, params);
        }

        if (!params.getFilters().isEmpty()) {
            System.out.println(params.getFilters());
        }

        if (!params.getFields().isEmpty()) {

        }

        final Page<T> page = this.repository.findAll(pageable);

        return this.buildResponse(page);
    }

    protected Pageable configSort(Pageable pageable, ApiQueryParams params) {

        Iterable<SortItemRequestBody> sorts = params.getSorts().orElse(new ArrayList<>());

        ArrayList<String> ascSorts = new ArrayList<>();
        ArrayList<String> descSorts = new ArrayList<>();

        sorts.forEach(sortItemRequestBody -> {
            if (sortItemRequestBody.getValue().toUpperCase().equals("ASC")) {
                ascSorts.add(sortItemRequestBody.getKey());
                return;
            }

            if (sortItemRequestBody.getValue().toUpperCase().equals("DESC")) {
                descSorts.add(sortItemRequestBody.getKey());
            }
        });

        if (ascSorts.isEmpty() && descSorts.isEmpty()) {
            return pageable;
        }

        Sort sort = Sort.unsorted();

        if (!ascSorts.isEmpty()) {
            sort = sort.and(Sort.by(Sort.Direction.ASC, ascSorts.toArray(new String[ascSorts.size()])));
        }

        if (!descSorts.isEmpty()) {
            sort.and(Sort.by(Sort.Direction.DESC, descSorts.toArray(new String[descSorts.size()])));
        }

        if (sort.isUnsorted()) {
            return pageable;
        }

        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sort
        );
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
