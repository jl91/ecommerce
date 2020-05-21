package com.profectusweb.ecommerce.requests;

public class SortItemRequestBody {

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public SortItemRequestBody setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SortItemRequestBody setValue(String value) {
        this.value = value;
        return this;
    }
}
