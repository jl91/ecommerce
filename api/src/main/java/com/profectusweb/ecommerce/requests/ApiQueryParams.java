package com.profectusweb.ecommerce.requests;

public class ApiQueryParams {

    private String fields;

    private String filters;

    private String sorts;

    private Integer limit;

    private Integer page;

    private String search;

    public String getFields() {
        return fields;
    }

    public ApiQueryParams setFields(String fields) {
        this.fields = fields;
        return this;
    }

    public String getFilters() {
        return filters;
    }

    public ApiQueryParams setFilters(String filters) {
        this.filters = filters;
        return this;
    }

    public String getSorts() {
        return sorts;
    }

    public ApiQueryParams setSorts(String sorts) {
        this.sorts = sorts;
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public ApiQueryParams setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public ApiQueryParams setPage(Integer page) {
        this.page = page;
        return this;
    }

    public String getSearch() {
        return search;
    }

    public ApiQueryParams setSearch(String search) {
        this.search = search;
        return this;
    }
}
