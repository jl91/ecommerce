package com.profectusweb.ecommerce.response;

import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
public class PageableResponse<T> {

    private Iterable<T> data;

    private Integer status;

    private PaginationMetadata paginationMetadata;

    public Iterable<T> getData() {
        return data;
    }

    public PageableResponse<T> setData(Iterable<T> data) {
        this.data = data;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public PageableResponse<T> setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public PaginationMetadata getPaginationMetadata() {
        return paginationMetadata;
    }

    public PageableResponse<T> setPaginationMetadata(PaginationMetadata paginationMetadata) {
        this.paginationMetadata = paginationMetadata;
        return this;
    }
}
