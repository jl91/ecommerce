package com.profectusweb.ecommerce.response;

public class PaginationMetadata {

    private Integer page;
    private Integer itemsPerPage;
    private Integer totalPages;
    private Integer total;

    PaginationMetadata() {

    }

    PaginationMetadata(Integer page) {
        this.setPage(page);
    }

    PaginationMetadata(Integer page, Integer itemsPerPage) {
        this(page);
        this.setItemsPerPage(itemsPerPage);
    }

    public PaginationMetadata(Integer page, Integer itemsPerPage, Integer total) {
        this(page, itemsPerPage);
        this.setTotal(total);
    }

    public PaginationMetadata(Integer page, Integer itemsPerPage, Integer total, Integer totalPages) {
        this(page, itemsPerPage, total);
        this.setTotalPages(totalPages);
    }

    public Integer getPage() {
        return page;
    }

    public PaginationMetadata setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getItemsPerPage() {
        return itemsPerPage;
    }

    public PaginationMetadata setItemsPerPage(Integer itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public PaginationMetadata setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public PaginationMetadata setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }
}
