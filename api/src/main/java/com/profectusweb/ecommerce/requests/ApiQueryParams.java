package com.profectusweb.ecommerce.requests;

import java.util.Optional;

public class ApiQueryParams {

    private Optional<String> fields;

    private Optional<String> filters;

    private Optional<Iterable<SortItemRequestBody>> sorts;

    private Optional<Integer> limit;

    private Optional<Integer> page;

    private Optional<String> search;

    ApiQueryParams() {

    }

    ApiQueryParams(String fields) {
        this();
        this.setFields(fields);
    }

    ApiQueryParams(String fields, String filters) {
        this(fields);
        this.setFilters(filters);
    }

    ApiQueryParams(
            String fields,
            String filters,
            Optional<Iterable<SortItemRequestBody>> sorts
    ) {
        this(fields, filters);
        this.setSorts(sorts);
    }

    ApiQueryParams(
            String fields,
            String filters,
            Optional<Iterable<SortItemRequestBody>> sorts,
            Integer limit
    ) {
        this(fields, filters, sorts);
        this.setLimit(limit);
    }

    ApiQueryParams(
            String fields,
            String filters,
            Optional<Iterable<SortItemRequestBody>> sorts,
            Integer limit,
            Integer page
    ) {
        this(fields, filters, sorts, limit);
        this.setPage(page);
    }

    public ApiQueryParams(
            String fields,
            String filters,
            Optional<Iterable<SortItemRequestBody>> sorts,
            Integer limit,
            Integer page,
            String search
    ) {
        this(fields, filters, sorts, limit, page);
        this.setSearch(search);
    }

    public Optional<String> getFields() {
        return fields;
    }

    public ApiQueryParams setFields(String fields) {
        this.fields = this.createOptional(fields);
        return this;
    }

    public Optional<String> getFilters() {
        return filters;
    }

    public ApiQueryParams setFilters(String filters) {
        this.filters = this.createOptional(filters);
        return this;
    }

    public Optional<Integer> getLimit() {
        return limit;
    }

    public ApiQueryParams setLimit(Integer limit) {
        this.limit = (limit == null || limit <= 0 || limit > 100)
                ? Optional.of(10)
                : Optional.of(limit);
        return this;
    }

    public Optional<Integer> getPage() {
        return page;
    }

    public ApiQueryParams setPage(Integer page) {
        this.page = (page == null || page <= 0)
                ? Optional.of(1)
                : Optional.of(page);
        return this;
    }

    public Optional<String> getSearch() {
        return search;
    }

    public ApiQueryParams setSearch(String search) {
        this.search = this.createOptional(search);
        return this;
    }

    private Optional<String> createOptional(String input) {
        if (
                input == null
                        || "".equals(input)
                        || "empty".equals(input)
        ) {
            return Optional.empty();
        }
        return Optional.of(input);
    }

    public Optional<Iterable<SortItemRequestBody>> getSorts() {
        return sorts;
    }

    public ApiQueryParams setSorts(Optional<Iterable<SortItemRequestBody>> sorts) {
        this.sorts = sorts;
        return this;
    }
}
